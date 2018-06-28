package example.com.apt_processor.sp;

import com.squareup.javapoet.TypeName;

/**
 * 构建方法参数
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class MethodInfo {

    public String key;                  //config,用于存在sharedPreference中的key
    public String methodName;           //生成一个方法名，比如 get_config;
    public String buildSpMethod;        //针对sharedPreference映射方法，比如 get_config是获取String类型，则buildSpMethod为getString
    public TypeName returnType;         //返回类型，buildSpMethod对应方法返回的类型
    public TypeName parameterType;      //参数类型，get_config 需要传递的参数

    public MethodInfo(String defaultKey, String methodName, String buildSpMethod, TypeName returnType, TypeName parameterType) {
        this.key = defaultKey;
        this.buildSpMethod = buildSpMethod;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterType = parameterType;
    }
}
