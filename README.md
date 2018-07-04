### SharedPreferencesInjector
[![](https://travis-ci.org/YummyLau/SharedPreferencesInjector.svg?branch=master)](https://travis-ci.org/YummyLau/SharedPreferencesInjector)
![Language](https://img.shields.io/badge/language-java-orange.svg)
![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Size](https://img.shields.io/badge/size-11K-brightgreen.svg)

#### 用于做什么
*SharedPreferencesInjector* 通过 APT 及 javaPoet 在编译期获取自定义注解生成对应 SharedPreferences Helper操作类，减少编写大量重复代码 。

在所需要的模块中定义一个配置类，用于存储ShaoredPreferences想要存储的数据，举个例子
```
@SharedPreferencesFile(fileName = "filename_if_you_want")
public class Config {

    @SharedPreferencesField(key = "key_if_you_want")
    public int readCount;

    @SharedPreferencesField()
    public String configKey;

    @SharedPreferencesField(key = "user_followed")
    public Set<String> setStringData;
}
```
编译项目后得到
```
public final class Config_SPHelper {
  static final String FILENAME_IF_YOU_WANT = "apt.sharedpreferencesprocessor.filename_if_you_want";

  public static final int get_key_if_you_want(int defaultValue) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).getInt("key_if_you_want",defaultValue);
  }

  public static final boolean put_key_if_you_want(int value) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).putInt("key_if_you_want",value);
  }

  public static final void put_key_if_you_want_async(int value) {
    com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).asyncPutInt("key_if_you_want",value);
  }

  public static final String get_configKey(String defaultValue) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).getString("configKey",defaultValue);
  }

  public static final boolean put_configKey(String value) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).putString("configKey",value);
  }

  public static final void put_configKey_async(String value) {
    com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).asyncPutString("configKey",value);
  }

  public static final Set<String> get_user_followed(Set<String> defaultValue) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).getStringSet("user_followed",defaultValue);
  }

  public static final boolean put_user_followed(Set<String> value) {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).putStringSet("user_followed",value);
  }

  public static final void put_user_followed_async(Set<String> value) {
    com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).asyncPutStringSet("user_followed",value);
  }

  public static final boolean clear() {
    return com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).clear();
  }

  public static final void clear_async() {
    com.effective.android.core.sp.SharedPreferenceInjector.getInstance(FILENAME_IF_YOU_WANT).asyncClear();
  }
}
```
而你只需要在项目使用你定义的 config数据
```
//建议在application中初始化
SharedPreferenceInjector.init(this);

//操作config数据
boolean result = Config_SPHelper.put_configKey("newKey");
Config_SPHelper.put_configKey_async("newKey");
Config_SPHelper.get_configKey("defaultKey");

//clear Config data
Config_SPHelper.clear();
Config_SPHelper.clear_async();
```

#### 如何引用
app build.gradle 添加
```
annotationProcessor 'com.effective.android:sp-compiler:1.0.0'   
compile 'com.effective.android:sp-core:1.0.0'
```

协议
