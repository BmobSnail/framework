package chengxinet.chengxilibs.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/6/1.
 */
public class ThumbnailUtil {

    public static final String Quality_720P = "_720";
    public static final String Quality_360P = "_360";
    public static final String Quality_180P = "_180";

    public static String getThum(String originalUrl, String quality) {
        String newUrl = "";

        if (!TextUtils.isEmpty(originalUrl)) {
            String temp = "";

            if(originalUrl.endsWith(".jpg"))
                temp = originalUrl.replace(".jpg", "");

            if(originalUrl.endsWith(".png"))
                temp = originalUrl.replace(".png", "");

            newUrl += temp + quality + ".jpg";
        }
        return newUrl;
    }
}
