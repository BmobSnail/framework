package chengxinet.chengxilibs.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2016/4/6.
 */
public class MyLog {

//    private static boolean isOpen = BuildConfig.LOG_DEBUG;
    private static boolean isOpen = true;

    public static void init(boolean isOpen) {
        MyLog.isOpen = isOpen;
    }

    public static void e(String tag, String content){
        if(isOpen) Log.e(tag, content);
    }

    public static void d(String tag, String content){
        if(isOpen) Log.d(tag, content);
    }

    public static void i(String tag, String content){
        if(isOpen) Log.i(tag, content);
    }

    /**
     * 使用 Logger 工具
     */

    public static void logv(String tag, String message) {
        if (isOpen) {
            Logger.init(tag);
            Logger.v(message);
        }
    }

    public static void logd(String tag, String message) {
        if(isOpen) {
            Logger.init(tag);
            Logger.d(message);
        }
    }

    public static void logi(String tag, String message) {
        if(isOpen) {
            Logger.init(tag);
            Logger.i(message);
        }
    }

    public static void logw(String tag, String message) {
        if(isOpen) {
            Logger.init(tag);
            Logger.w(message);
        }
    }

    public static void loge(String tag, String message) {
        if(isOpen) {
            Logger.init(tag);
            Logger.e(message);
        }
    }

    public static void loge(String tag, String message, Exception e) {
        if(isOpen) {
            Logger.init(tag);
            Logger.e(message, e);
        }
    }

    public static void logj(String tag, String message) {
        if (isOpen) {
            Logger.init(tag);
            Logger.json(message);
        }
    }
}
