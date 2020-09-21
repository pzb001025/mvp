package com.angle.mvplib.data.net.ok;

import com.angle.mvplib.common.Constarct;
import com.angle.mvplib.data.net.ok.converter.MvpGsonConverterFactory;
import com.trello.rxlifecycle4.android.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

//创建一个网络封装
public class DataService {
    //    创建等待时间
    private static final int WAIT_TIME = 10;
    //    创建Apiservice成员变量(volatile) 防止对象重排序
    public static volatile ApiService mApiService;

    //    创建方法返回ApiServiec对象
    public static ApiService getApiService() {
        if (mApiService == null) {
//            保证线程安全对象同步
            synchronized (DataService.class) {
                if (mApiService == null) {
//                    获得log日志
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    if (BuildConfig.DEBUG) {
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    } else {
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }
//                    创建okHttp获得网络日志
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(WAIT_TIME, TimeUnit.SECONDS)
                            .readTimeout(WAIT_TIME, TimeUnit.SECONDS)
                            .writeTimeout(WAIT_TIME, TimeUnit.SECONDS)
                            .addInterceptor(httpLoggingInterceptor)
                            .build();
//                    创建retrofit返回APiService对象
                    Retrofit retrofit = new Retrofit.Builder()
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .addConverterFactory(MvpGsonConverterFactory.create())
                            .baseUrl(Constarct.BASE_URL)
                            .client(okHttpClient)
                            .build();
                    return retrofit.create(ApiService.class);

                }
            }
        }
        return mApiService;
    }
}
