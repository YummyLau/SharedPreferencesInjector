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
@SharedPreferencesFile(fileName = "filename_if_you_want")
public class Config {

    @SharedPreferencesField(key = "key_if_you_want")
    public int readCount;

    @SharedPreferencesField()
    public String configKey;

    @SharedPreferencesField(key = "user_followed")
    public Set<String> setStringData;

}
