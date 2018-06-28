package example.com.apt_processor.sp;

/**
 * 构建class参数
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class ClassInfo {

    public String fileName;                  //对应Preferences中的name，标识一个配置文件，为了保障生成的name不受不同包中相同类名的类影响，fileName前缀统一为包名
    public String staticFileNameStr;         //fileName 对应的大写字母，用于生成静态标量使用
    public String className;

    public ClassInfo(String className,String fileName, String staticFileNameStr) {
        this.className = className;
        this.fileName = fileName;
        this.staticFileNameStr = staticFileNameStr;
    }
}
