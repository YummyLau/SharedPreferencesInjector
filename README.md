### SharedPreferencesInjector
[![](https://travis-ci.org/YummyLau/SharedPreferencesInjector.svg?branch=master)](https://travis-ci.org/YummyLau/SharedPreferencesInjector)
![Language](https://img.shields.io/badge/language-java-orange.svg)
![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Size](https://img.shields.io/badge/size-11K-brightgreen.svg)

README: [English](https://github.com/YummyLau/SharedPreferencesInjector/blob/master/README.md) | [中文](https://github.com/YummyLau/SharedPreferencesInjector/blob/master/README-zh.md)

#### What to do

In daily development you may define a SharedPreferencesUtils helper for handling lightweight data storage, but there are still a large number of static variables in the project that identify a key for a piece of data or a piece of code that handles storage. It is hoped that a certain class of lightweight data can be aggregated and processed in a unified manner, and the writing of a large amount of duplicate code is reduced.

*SharedPreferencesInjector* Get custom annotations at compile time through APT and javaPoet technology to generate the corresponding SharedPreferences Helper operation class to help solve the above problems.

* Define a class that aggregates certain configurations

```
@SharedPreferencesFile(fileName = "filename_if_you_want")       //can set the corresponding stored SharedPreferences file name
public class Config {

    @SharedPreferencesField(key = "key_if_you_want")            //define the key you need. If you don't set it, the default is the variable name.
    public int readCount;

    @SharedPreferencesField()
    public String configKey;

    @SharedPreferencesField()
    public Set<String> setStringData;
}
```

* After compiling, the generated class corresponding to {class name}_SPHelper, you can directly call the operation

```
//global initialization once, it is recommended to call in application#create
SharedPreferenceInjector.init(this);

//operation Config data
boolean result = Config_SPHelper.put_configKey("newKey");       //同步操作
Config_SPHelper.put_configKey_async("newKey");                  //异步操作
Config_SPHelper.get_configKey("defaultKey");                    //获取

//clear Config data
Config_SPHelper.clear();
Config_SPHelper.clear_async();
```

#### How to use
Add dependencies in module **build.gradle** file
```
annotationProcessor 'com.effective.android:sp-compiler:1.0.0'   
compile 'com.effective.android:sp-core:1.0.0'
```

#### Expect
The project was written only to improve the efficiency of day-to-day development and focus on the business. Javapoet is a good thing, you can also refer to the three modules under the apt file, and use annotation extension to meet your own project needs.

If you have a better practice or suggestions, please write to yummyl.lau@gmail.com.
