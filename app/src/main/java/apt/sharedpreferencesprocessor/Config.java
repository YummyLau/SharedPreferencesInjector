package apt.sharedpreferencesprocessor;


import com.effective.android.annotation.sp.SharedPreferencesField;
import com.effective.android.annotation.sp.SharedPreferencesFile;

import java.util.Set;



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
