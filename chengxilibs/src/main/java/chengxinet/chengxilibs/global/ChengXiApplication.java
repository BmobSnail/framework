package chengxinet.chengxilibs.global;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;


/**
 * Created by Administrator on 2016/4/6.
 */
public abstract class ChengXiApplication extends MultiDexApplication {

    private File cacheDir;

    @Override
    public void onCreate() {
        super.onCreate();

        setCacheDir(getCacheDirPath());
        init();


        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(cacheDir)
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setSmallImageDiskCacheConfig(diskCacheConfig)
                .build();


        Fresco.initialize(this, config);

    }

    private void setCacheDir(String path) {
        if (!TextUtils.isEmpty(path)) {
            cacheDir = getOwnCacheDirectory(this, path);
        }
    }

    public abstract void init();

    public abstract String getCacheDirPath();

    public abstract String getApiUrl();

    public abstract Object getAipImpl();

    public abstract Class<? extends Dialog> getProgressDialogClass();

    public abstract int getActionBarBgColor();

    protected static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;

        if ("mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        }

        if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        }

        return appCacheDir;
    }

    protected static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }


    public File getCacheFile() {
        if (cacheDir == null)
            setCacheDir(getCacheDirPath());
        return cacheDir;
    }



}
