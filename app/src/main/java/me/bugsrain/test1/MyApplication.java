package me.bugsrain.test1;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import chengxinet.chengxilibs.global.ChengXiApplication;

/**
 * Created by quan on 16/8/25.
 */

public class MyApplication extends ChengXiApplication {

    private static MyApplication mMyApplication;

    @Override
    public void init() {
        mMyApplication = this;;
    }

    @Override
    public String getCacheDirPath() {
        return null;
    }

    @Override
    public String getApiUrl() {
        return null;
    }

    @Override
    public Object getAipImpl() {
        return null;
    }

    @Override
    public Class<? extends ProgressDialog> getProgressDialogClass() {
        return ProgressDialog.class;
    }


    @Override
    public int getActionBarBgColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    public static Context getAppContext() {
        return mMyApplication;
    }

}
