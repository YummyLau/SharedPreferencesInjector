package apt.sharedpreferencesprocessor;

import java.util.Set;

import example.com.apt_annotation.sp.SharedPreferencesField;
import example.com.apt_annotation.sp.SharedPreferencesFile;

/**
 * test for spprocessor
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
@SharedPreferencesFile(fileName = "config_file")
public class Config {

    @SharedPreferencesField()
    public int intData;

    @SharedPreferencesField()
    public Integer IntegerData;

    @SharedPreferencesField()
    public float floatData;

    @SharedPreferencesField()
    public Float FloatData;

    @SharedPreferencesField()
    public boolean booleanData;

    @SharedPreferencesField()
    public Boolean BooleanData;

    @SharedPreferencesField()
    public long longData;

    @SharedPreferencesField()
    public Long LongData;

    @SharedPreferencesField
    public String stringData;

    @SharedPreferencesField
    public Set<String> setStringData;

}
