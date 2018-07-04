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

        Config_SPHelper.put_IntegerData(1);
        Config_SPHelper.put_intData(2);
        Config_SPHelper.put_floatData(1.0f);
        Config_SPHelper.put_FloatData(2.0f);
        Config_SPHelper.put_booleanData(true);
        Config_SPHelper.get_BooleanData(false);
        Config_SPHelper.put_stringData("233333");
        Set<String> stringSet = new HashSet<>();
        stringSet.add("1333333");
        stringSet.add("2333333");
        Config_SPHelper.put_setStringData(stringSet);

        TextView textView = findViewById(R.id.text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Config_SPHelper.get_IntegerData(3));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_intData(4));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_floatData(3.0f));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_FloatData(3.0f));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_booleanData(false));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_BooleanData(true));
        stringBuilder.append("\n");

        stringBuilder.append(Config_SPHelper.get_stringData("32222222"));
        stringBuilder.append("\n");

        Set<String> strings = Config_SPHelper.get_setStringData(null);
        if (strings != null && !strings.isEmpty()) {
            for (String s : strings) {
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        }
        textView.setText(stringBuilder.toString());
    }
}
