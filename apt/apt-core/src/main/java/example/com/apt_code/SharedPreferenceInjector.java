package example.com.apt_code;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by yummyLau on 2018/6/23.
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */

public class SharedPreferenceInjector {

    private static final String TAG = SharedPreferenceInjector.class.getSimpleName();
    private static Context sApplicationContext;
    private static HashMap<String, WeakReference<SharedPreferenceInjector>> sInstances = new HashMap<>();
    private static boolean init = false;


    private SharedPreferences mSharedPreferences;

    private SharedPreferenceInjector(@NonNull SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public static void init(@NonNull Context context) {
        if (context == null) {
            throw new RuntimeException(TAG + "#init: context can't be null !");
        }
        if (!init) {
            init = true;
            sApplicationContext = context;
        }
    }

    public static SharedPreferenceInjector getInstance(@NonNull String preferenceName) {
        return getInstance(preferenceName, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceInjector getInstance(String preferenceName, int mode) {
        if (sApplicationContext == null) {
            throw new RuntimeException(TAG + "#getInstance should be invoked before invoking #init !");
        }
        WeakReference<SharedPreferenceInjector> weakReference = sInstances.get(preferenceName);
        SharedPreferenceInjector sharedPreferenceUtil = weakReference == null ? null : weakReference.get();
        if (sharedPreferenceUtil == null) {
            sharedPreferenceUtil = new SharedPreferenceInjector(sApplicationContext.getSharedPreferences(preferenceName, mode));
            sInstances.put(preferenceName, new WeakReference<>(sharedPreferenceUtil));
        }
        return sharedPreferenceUtil;
    }

    /**
     * 处理string
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public boolean putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putString(key, value).commit();
    }

    public void asyncPutString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * 处理 string set
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(key, defaultValue);
    }

    public boolean putStringSet(String key, Set<String> values) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putStringSet(key, values).commit();
    }

    public void asyncPutStringSet(String key, Set<String> values) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putStringSet(key, values).apply();
    }


    /**
     * 处理 int
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putInt(key, value).commit();
    }

    public void asyncPutInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value).apply();
    }

    /**
     * 处理boonlean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putBoolean(key, value).commit();
    }

    public void asyncPutBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * 处理float
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putFloat(key, value).commit();
    }

    public void asyncPutFloat(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value).apply();
    }

    /**
     * 处理 long
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.putLong(key, value).commit();
    }

    public void asyncPutLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value).apply();
    }


    public boolean clear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        return editor.clear().commit();
    }

    public void asyncClear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear().apply();
    }
}
