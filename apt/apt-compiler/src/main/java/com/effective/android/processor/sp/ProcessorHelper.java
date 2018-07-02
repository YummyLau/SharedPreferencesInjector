package com.effective.android.processor.sp;

import com.effective.android.annotation.sp.SharedPreferencesFile;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;


/**
 * 助手类
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class ProcessorHelper {

    public static MethodInfo buildGetMethodInfo(VariableElement element, String key) {
        if (element == null) {
            throw new IllegalArgumentException("element can't be null for #buildGetMethodInfo !");
        }
        String defaultKey = element.getSimpleName().toString();
        if (key != null && !key.equals("")) {
            defaultKey = key;
        }
        String methodName = Constants.GET_START_ + defaultKey;
        String buildSpMethod;
        TypeName returnType;
        TypeName parameterType;

        switch (element.asType().toString()) {
            case Constants.JavaType.STRING: {
                returnType = ClassName.get(String.class);
                parameterType = ClassName.get(String.class);
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_STRING;
                break;
            }
            case Constants.JavaType.INTEGER: {
                returnType = TypeName.INT;
                parameterType = TypeName.INT;
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.INTEGER_BOXED: {
                returnType = ClassName.get(Integer.class);
                parameterType = ClassName.get(Integer.class);
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.FLOAT: {
                returnType = TypeName.FLOAT;
                parameterType = TypeName.FLOAT;
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.FLOAT_BOXED: {
                returnType = ClassName.get(Float.class);
                parameterType = ClassName.get(Float.class);
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN: {
                returnType = TypeName.BOOLEAN;
                parameterType = TypeName.BOOLEAN;
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN_BOXED: {
                returnType = ClassName.get(Boolean.class);
                parameterType = ClassName.get(Boolean.class);
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.LONG: {
                returnType = TypeName.LONG;
                parameterType = TypeName.LONG;
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.LONG_BOXED: {
                returnType = ClassName.get(Long.class);
                parameterType = ClassName.get(Long.class);
                buildSpMethod = Constants.GET_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.STRING_SET: {
                returnType = ParameterizedTypeName.get(Set.class, String.class);
                parameterType = ParameterizedTypeName.get(Set.class, String.class);
                buildSpMethod = Constants.GET_START + "StringSet";
                break;
            }
            default: {
                throw new IllegalArgumentException("can't support " + element.asType().toString() + " type for #buildGetMethodInfo !");
            }
        }
        return new MethodInfo(defaultKey, methodName, buildSpMethod, returnType, parameterType);
    }

    public static MethodInfo buildPutMethodInfo(VariableElement element, String key) {
        if (element == null) {
            throw new IllegalArgumentException("element can't be null for #buildPutMethodInfo !");
        }
        String defaultKey = element.getSimpleName().toString();
        if (key != null && !key.equals("")) {
            defaultKey = key;
        }
        String methodName = Constants.PUT_START_ + defaultKey;
        String buildSpMethod;
        TypeName returnType;
        TypeName parameterType;

        switch (element.asType().toString()) {
            case Constants.JavaType.STRING: {
                returnType = TypeName.BOOLEAN;
                parameterType = ClassName.get(String.class);
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_STRING;
                break;
            }
            case Constants.JavaType.INTEGER: {
                returnType = TypeName.BOOLEAN;
                parameterType = TypeName.INT;
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.INTEGER_BOXED: {
                returnType = TypeName.BOOLEAN;
                parameterType = ClassName.get(Integer.class);
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.FLOAT: {
                returnType = TypeName.BOOLEAN;
                parameterType = TypeName.FLOAT;
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.FLOAT_BOXED: {
                returnType = TypeName.BOOLEAN;
                parameterType = ClassName.get(Float.class);
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN: {
                returnType = TypeName.BOOLEAN;
                parameterType = TypeName.BOOLEAN;
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN_BOXED: {
                returnType = TypeName.BOOLEAN;
                parameterType = ClassName.get(Boolean.class);
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.LONG: {
                returnType = TypeName.BOOLEAN;
                parameterType = TypeName.LONG;
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.LONG_BOXED: {
                returnType = TypeName.BOOLEAN;
                parameterType = ClassName.get(Long.class);
                buildSpMethod = Constants.PUT_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.STRING_SET: {
                returnType = TypeName.BOOLEAN;
                parameterType = ParameterizedTypeName.get(Set.class, String.class);
                buildSpMethod = Constants.PUT_START + "StringSet";
                break;
            }
            default: {
                throw new IllegalArgumentException("can't support " + element.asType().toString() + " type for #buildPutMethodInfo !");
            }
        }
        return new MethodInfo(defaultKey, methodName, buildSpMethod, returnType, parameterType);
    }

    public static MethodInfo buildAsyncPutMethodInfo(VariableElement element, String key) {
        if (element == null) {
            throw new IllegalArgumentException("element can't be null for #buildPutMethodInfo !");
        }
        String defaultKey = element.getSimpleName().toString();
        if (key != null && !key.equals("")) {
            defaultKey = key;
        }
        String methodName = Constants.PUT_START_ + defaultKey + Constants._ASYNC_END;
        String buildSpMethod;
        TypeName returnType;
        TypeName parameterType;

        switch (element.asType().toString()) {
            case Constants.JavaType.STRING: {
                returnType = TypeName.VOID;
                parameterType = ClassName.get(String.class);
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_STRING;
                break;
            }
            case Constants.JavaType.INTEGER: {
                returnType = TypeName.VOID;
                parameterType = TypeName.INT;
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.INTEGER_BOXED: {
                returnType = TypeName.VOID;
                parameterType = ClassName.get(Integer.class);
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_INTEGER.substring(0, 1).toUpperCase() + Constants.SIMPLE_INTEGER.substring(1);
                break;
            }
            case Constants.JavaType.FLOAT: {
                returnType = TypeName.VOID;
                parameterType = TypeName.FLOAT;
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.FLOAT_BOXED: {
                returnType = TypeName.VOID;
                parameterType = ClassName.get(Float.class);
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_FLOAT_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN: {
                returnType = TypeName.VOID;
                parameterType = TypeName.BOOLEAN;
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.BOOLEAN_BOXED: {
                returnType = TypeName.VOID;
                parameterType = ClassName.get(Boolean.class);
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_BOOLEAN_BOXED;
                break;
            }
            case Constants.JavaType.LONG: {
                returnType = TypeName.VOID;
                parameterType = TypeName.LONG;
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.LONG_BOXED: {
                returnType = TypeName.VOID;
                parameterType = ClassName.get(Long.class);
                buildSpMethod = Constants.ASYNC_PUT_START + Constants.SIMPLE_LONG_BOXED;
                break;
            }
            case Constants.JavaType.STRING_SET: {
                returnType = TypeName.VOID;
                parameterType = ParameterizedTypeName.get(Set.class, String.class);
                buildSpMethod = Constants.ASYNC_PUT_START + "StringSet";
                break;
            }
            default: {
                throw new IllegalArgumentException("can't support " + element.asType().toString() + " type for #buildPutMethodInfo !");
            }
        }
        return new MethodInfo(defaultKey, methodName, buildSpMethod, returnType, parameterType);
    }

    public static MethodInfo buildClearMethodInfo() {
        String defaultKey = null;
        String methodName = Constants.CLEAR;
        String buildSpMethod = Constants.CLEAR;
        TypeName returnType = TypeName.BOOLEAN;
        TypeName parameterType = TypeName.VOID;
        return new MethodInfo(defaultKey, methodName, buildSpMethod, returnType, parameterType);
    }

    public static MethodInfo buildAsyncClearMethodInfo() {
        String defaultKey = null;
        String methodName = Constants.CLEAR + Constants._ASYNC_END;
        String buildSpMethod = Constants.ASYNC_CLEAR;
        TypeName returnType = TypeName.VOID;
        TypeName parameterType = TypeName.VOID;
        return new MethodInfo(defaultKey, methodName, buildSpMethod, returnType, parameterType);
    }

    public static void preventHasSameMethodInOneClass(List<MethodSpec> methodSpecs, TypeElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can't be null for #preventHasSameMethodInOneClass !");
        }

        if (methodSpecs == null || methodSpecs.isEmpty()) {
            return;
        }
        int methodSize = methodSpecs.size();
        Set<String> methodNameSet = new HashSet<>();
        for (MethodSpec methodSpec : methodSpecs) {
            if (methodSpec.name != null) {
                methodNameSet.add(methodSpec.name);
            }
        }
        if (methodSize != methodNameSet.size()) {
            throw new RuntimeException("there are some SharedPreferencesFields that has same key in a SharedPreferencesFile ！ code at : " + element.getQualifiedName());
        }
    }

    public static ClassInfo buildClassInfo(SharedPreferencesFile annotation, TypeElement element) {
        if (annotation == null || element == null) {
            throw new IllegalArgumentException("annotation or element can't be null for #buildClassInfo !");
        }

        String fileName = element.getQualifiedName().toString();
        String key = element.getSimpleName().toString();
        String className = key + Constants.CLASS_NAME_APPEND;

        //为了保障生成的name不受不同包中相同类名的类影响，fileName前缀统一为包名
        if (!annotation.fileName().equals("")) {
            int num = key.length();
            fileName = fileName.substring(0, fileName.length() - num);
            key = annotation.fileName();
            fileName = fileName + key;
        }
        key = key.toUpperCase();
        return new ClassInfo(className, fileName, key);
    }
}
