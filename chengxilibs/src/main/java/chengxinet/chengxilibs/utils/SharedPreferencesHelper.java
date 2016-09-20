package chengxinet.chengxilibs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2015/12/7.
 */
public class SharedPreferencesHelper {

    private final static String TAG = "SharedPreferencesHelper";
    private static SharedPreferencesHelper helper;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private SharedPreferencesHelper(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferencesHelper getInstance(Context context){
        if(helper == null){
            helper = new SharedPreferencesHelper(context.getApplicationContext());
        }

        return helper;
    }

    public boolean putBoolean(String key, boolean value) {
        editor = prefs.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    /**
     * 根据键字符串，存储一个字符串值
     *
     * @param key
     * @param value
     * @return 返回提交是否成功
     */
    public boolean putString(String key, String value) {
        editor = prefs.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 根据key值得到存储结果，如果没有找到value就返回null
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return prefs.getString(key, "");
    }

    public String getString(String key,String def) {
        return prefs.getString(key, def);
    }

    public long getLong(String key,long def){
        return prefs.getLong(key, def);
    }

    public boolean putLong(String key, long v){
        editor = prefs.edit();
        editor.putLong(key, v);
        return editor.commit();
    }

    /**
     * 根据键字符串，存储一个字符串值
     *
     * @param key
     * @param value
     * @return 返回提交是否成功
     */
    public boolean putInt(String key, int value) {
        editor = prefs.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 根据key值得到存储结果，如果没有找到value就返回-1
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return prefs.getInt(key, -1);
    }

    /**
     * 清空数据
     *
     * @return
     */
    public boolean clear() {
        editor = prefs.edit();
        editor.clear();
        return editor.commit();
    }

    public boolean remove(String key) {
        editor = prefs.edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * 关闭当前对象
     *
     * @return
     */
    public void close() {
        prefs = null;
    }
}
