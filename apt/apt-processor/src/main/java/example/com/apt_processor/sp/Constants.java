package example.com.apt_processor.sp;

/**
 * 常量管理
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
public class Constants {

    public static final String VALUE = "value";
    public static final String DEFAULT_VALUE = "defaultValue";
    public static final String MODULE_NAME = "moduleName";

    public static final String CLASS_NAME_APPEND = "_SPHelper";
    public static final String GET_START = "get";
    public static final String GET_START_ = "get_";
    public static final String PUT_START = "put";
    public static final String ASYNC_PUT_START = "asyncPut";
    public static final String ASYNC_CLEAR = "asyncClear";
    public static final String _ASYNC_END = "_async";
    public static final String PUT_START_ = "put_";
    public static final String CLEAR = "clear";
    public static final String POINT = ".";
    public static final String LEFT_ANGLE_BRACKETS = "<";
    public static final String RIGHT_ANGLE_BRACKETS = ">";
    public static final String SIMPLE_BYTE = "byte";
    public static final String SIMPLE_BYTE_BOX = "Byte";
    public static final String SIMPLE_SHORE = "Short";
    public static final String SIMPLE_SHORE_BOXED = "Short";
    public static final String SIMPLE_STRING = "String";
    public static final String SIMPLE_INTEGER = "int";
    public static final String SIMPLE_INTEGER_BOXED = "Integer";
    public static final String SIMPLE_LONG = "long";
    public static final String SIMPLE_LONG_BOXED = "Long";
    public static final String SIMPLE_FLOAT = "float";
    public static final String SIMPLE_FLOAT_BOXED = "Float";
    public static final String SIMPLE_BOOLEAN = "boolean";
    public static final String SIMPLE_BOOLEAN_BOXED = "Boolean";
    public static final String SIMPLE_DOUBLE = "double";
    public static final String SIMPLE_DOUBLE_BOXED = "Double";
    public static final String SIMPLE_SET = "Set";

    public interface JavaType {
        String LANG = "java.lang";
        String UTIL = "java.util";
        String BYTE = LANG + POINT + SIMPLE_BYTE;
        String SHORT = LANG + POINT + SIMPLE_SHORE_BOXED;
        String STRING = LANG + POINT + SIMPLE_STRING;
        String INTEGER = SIMPLE_INTEGER;
        String INTEGER_BOXED = LANG + POINT + SIMPLE_INTEGER_BOXED;
        String LONG = SIMPLE_LONG;
        String LONG_BOXED = LANG + POINT + SIMPLE_LONG_BOXED;
        String FLOAT = SIMPLE_FLOAT;
        String FLOAT_BOXED = LANG + POINT + SIMPLE_FLOAT_BOXED;
        String BOOLEAN = SIMPLE_BOOLEAN;
        String BOOLEAN_BOXED = LANG + POINT + SIMPLE_BOOLEAN_BOXED;
        String DOUBLE = SIMPLE_DOUBLE;
        String DOUBLE_BOXED = LANG + POINT + SIMPLE_DOUBLE_BOXED;
        String STRING_SET = UTIL + POINT + SIMPLE_SET + LEFT_ANGLE_BRACKETS + STRING + RIGHT_ANGLE_BRACKETS;
    }
}
