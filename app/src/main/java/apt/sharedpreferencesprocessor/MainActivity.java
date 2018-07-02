package apt.sharedpreferencesprocessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.effective.android.core.sp.SharedPreferenceInjector;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferenceInjector.init(this);
        Config_SPHelper.get_BooleanData(false);
    }
}
