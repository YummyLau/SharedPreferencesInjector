package example.com.apt_processor.sp;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import example.com.apt_annotation.sp.SharedPreferencesField;
import example.com.apt_annotation.sp.SharedPreferencesFile;

import static example.com.apt_processor.sp.Constants.DEFAULT_VALUE;
import static example.com.apt_processor.sp.Constants.VALUE;

/**
 * SharedPreferences生成代码
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 * <p>
 * 1. 待完善，控制不能key相同
 * 2. 新增set集合
 */
@AutoService(Processor.class)
public class SharedPreferencesProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Messager messager;

    @Override
    public Set<String> getSupportedOptions() {
        return Collections.singleton(Constants.MODULE_NAME);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(SharedPreferencesFile.class.getCanonicalName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(SharedPreferencesFile.class);
        for (Element element : elements) {

            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        String.format("Only classes can be annotated with @%s.", SharedPreferencesFile.class.getSimpleName()));
                return true;
            }

            TypeElement typeElement = (TypeElement) element;
            if (!isValidClass(typeElement)) {
                return true;
            }

            SharedPreferencesFile annotation = typeElement.getAnnotation(SharedPreferencesFile.class);
            ClassInfo classInfo = ProcessorHelper.buildClassInfo(annotation, typeElement);

            List<? extends Element> members = elementUtils.getAllMembers(typeElement);
            List<MethodSpec> methodList = new ArrayList<>();
            for (Element ele : members) {
                final boolean isVariableElement = ele instanceof VariableElement;
                if (!isVariableElement) {
                    continue;
                }
                SharedPreferencesField configMember = ele.getAnnotation(SharedPreferencesField.class);
                if (configMember == null) {
                    continue;
                }
                methodList.add(buildGetMethod((VariableElement) ele, classInfo.staticFileNameStr));
                methodList.add(buildPutMethod((VariableElement) ele, classInfo.staticFileNameStr));
                methodList.add(buildAsyncPutMethod((VariableElement) ele, classInfo.staticFileNameStr));
            }

            ProcessorHelper.preventHasSameMethodInOneClass(methodList, typeElement);

            if (!methodList.isEmpty()) {
                methodList.add(buildClearMethod(classInfo.staticFileNameStr));
                methodList.add(buildAsyncClearMethod(classInfo.staticFileNameStr));
                FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(String.class), classInfo.staticFileNameStr,
                        Modifier.FINAL, Modifier.STATIC)
                        .initializer(CodeBlock.of("\"" + classInfo.fileName + "\""))
                        .build();
                TypeSpec helperTypeSpec =
                        TypeSpec.classBuilder(classInfo.className)
                                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                .addField(fieldSpec)
                                .addMethods(methodList)
                                .build();
                writeNewFile(elementUtils.getPackageOf(typeElement).getQualifiedName().toString(), helperTypeSpec);
            }
        }
        return true;
    }

    private void writeNewFile(String packageName, TypeSpec typeSpec) {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MethodSpec buildGetMethod(VariableElement member, String name) {
        SharedPreferencesField configMember = member.getAnnotation(SharedPreferencesField.class);
        if (configMember == null) {
            return null;
        }
        MethodInfo methodInfo = ProcessorHelper.buildGetMethodInfo(member, configMember.key());

        MethodSpec.Builder getMethod = MethodSpec.methodBuilder(methodInfo.methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        StringBuffer stringBuffer = new StringBuffer("return example.com.apt_code.SharedPreferenceUtil.getInstance(" + name + ")");
        stringBuffer.append("." + methodInfo.buildSpMethod + "(" + "\"" + methodInfo.key + "\"" + "," + DEFAULT_VALUE + ")");

        getMethod.returns(methodInfo.returnType);
        getMethod.addParameter(methodInfo.parameterType, DEFAULT_VALUE);
        getMethod.addStatement(stringBuffer.toString());
        return getMethod.build();
    }

    private MethodSpec buildPutMethod(VariableElement member, String name) {
        SharedPreferencesField configMember = member.getAnnotation(SharedPreferencesField.class);
        if (configMember == null) {
            return null;
        }
        MethodInfo methodInfo = ProcessorHelper.buildPutMethodInfo(member, configMember.key());

        MethodSpec.Builder putMethod = MethodSpec.methodBuilder(methodInfo.methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        StringBuffer stringBuffer = new StringBuffer("return example.com.apt_code.SharedPreferenceUtil.getInstance(" + name + ")");
        stringBuffer.append("." + methodInfo.buildSpMethod + "(" + "\"" + methodInfo.key + "\"" + "," + VALUE + ")");

        putMethod.returns(methodInfo.returnType);
        putMethod.addParameter(methodInfo.parameterType, VALUE);
        putMethod.addStatement(stringBuffer.toString());
        return putMethod.build();
    }

    private MethodSpec buildAsyncPutMethod(VariableElement member, String name) {
        SharedPreferencesField configMember = member.getAnnotation(SharedPreferencesField.class);
        if (configMember == null) {
            return null;
        }
        MethodInfo methodInfo = ProcessorHelper.buildAsyncPutMethodInfo(member, configMember.key());

        MethodSpec.Builder putMethod = MethodSpec.methodBuilder(methodInfo.methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        StringBuffer stringBuffer = new StringBuffer("example.com.apt_code.SharedPreferenceUtil.getInstance(" + name + ")");
        stringBuffer.append("." + methodInfo.buildSpMethod + "(" + "\"" + methodInfo.key + "\"" + "," + VALUE + ")");

        putMethod.returns(methodInfo.returnType);
        putMethod.addParameter(methodInfo.parameterType, VALUE);
        putMethod.addStatement(stringBuffer.toString());
        return putMethod.build();
    }

    private MethodSpec buildClearMethod(String name) {
        MethodInfo methodInfo = ProcessorHelper.buildClearMethodInfo();

        MethodSpec.Builder putMethod = MethodSpec.methodBuilder(methodInfo.methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        StringBuffer stringBuffer = new StringBuffer("return example.com.apt_code.SharedPreferenceUtil.getInstance(" + name + ")");
        stringBuffer.append("." + methodInfo.buildSpMethod + "()");

        putMethod.returns(methodInfo.returnType);
        putMethod.addStatement(stringBuffer.toString());
        return putMethod.build();
    }

    private MethodSpec buildAsyncClearMethod(String name) {
        MethodInfo methodInfo = ProcessorHelper.buildAsyncClearMethodInfo();

        MethodSpec.Builder putMethod = MethodSpec.methodBuilder(methodInfo.methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);

        StringBuffer stringBuffer = new StringBuffer("example.com.apt_code.SharedPreferenceUtil.getInstance(" + name + ")");
        stringBuffer.append("." + methodInfo.buildSpMethod + "()");

        putMethod.returns(methodInfo.returnType);
        putMethod.addStatement(stringBuffer.toString());
        return putMethod.build();
    }

    private boolean isValidClass(TypeElement typeElement) {

        if (!typeElement.getModifiers().contains(Modifier.PUBLIC)) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    String.format("The class %s is not public.", typeElement.getQualifiedName().toString()));
            return false;
        }

        if (typeElement.getModifiers().contains(Modifier.ABSTRACT)) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    String.format("The class %s is abstract. You can't annotate abstract classes with @%",
                            typeElement.getQualifiedName().toString(), SharedPreferencesFile.class.getSimpleName()));
            return false;
        }

        for (Element enclosed : typeElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().size() == 0
                        && constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
                    return true;
                }
            }
        }
        messager.printMessage(Diagnostic.Kind.ERROR,
                String.format("The class %s must provide an public empty default constructor",
                        typeElement.getQualifiedName().toString()));
        return false;
    }


}
