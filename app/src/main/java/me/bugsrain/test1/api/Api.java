package me.bugsrain.test1.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import chengxinet.chengxilibs.utils.DES;
import chengxinet.chengxilibs.utils.MyLog;
import chengxinet.chengxilibs.utils.NetworkUtils;
import me.bugsrain.test1.MyApplication;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by baixiaokang on 16/3/9.
 */
public class Api {

    public static final int Success = 200;

    private static final Charset UTF8 = Charset.forName("UTF-8");
//    public static final String X_LC_Id = "i7j2k7bm26g7csk7uuegxlvfyw79gkk4p200geei8jmaevmx";
//    public static final String X_LC_Key = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3";
    private static final String BASE_URL = "http://tsyy.gdswf.com//v1/";

    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    private ApiService service;

    private OkHttpClient client;



    //构造方法私有
    private Api() {
        Interceptor jsonInterceptor = chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            Buffer buffer = source.buffer();
            source.request(Long.MAX_VALUE);
            String temp = buffer.readString(UTF8);
            String data = null;
            try {
                data = new DES().decode(temp);
                MyLog.logj("json", data);
                OutputStream stream = buffer.outputStream();
                stream.write(data.getBytes());
                stream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        };

        Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
//            .addHeader("X-LC-Id", X_LC_Id)
//            .addHeader("X-LC-Key", X_LC_Key)
                .addHeader("Content-Type", "application/json")
                .build());

        File cacheFile = new File(MyApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        client = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(mInterceptor)
                .addInterceptor(jsonInterceptor)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        service = retrofit.create(ApiService.class);

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(MyApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isNetworkAvailable(MyApplication.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }

    public ApiService getService() {
        return service;
    }
}