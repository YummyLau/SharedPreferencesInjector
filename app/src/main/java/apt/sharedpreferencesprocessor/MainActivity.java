package apt.sharedpreferencesprocessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.effective.android.core.sp.SharedPreferenceInjector;

import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferenceInjector.init(this);

        boolean result = Config_SPHelper.put_configKey("newKey");
        Config_SPHelper.put_configKey_async("newKey");
        Config_SPHelper.get_configKey("defaultKey");

        Config_SPHelper.clear();
        Config_SPHelper.clear_async();
    }
}
