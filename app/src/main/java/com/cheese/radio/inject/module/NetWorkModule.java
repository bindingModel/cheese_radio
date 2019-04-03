package com.cheese.radio.inject.module;

import com.cheese.radio.BuildConfig;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.converter.JsonConverterFactory;
import com.cheese.radio.inject.interceptor.UserInterceptor;
import com.cheese.radio.inject.scope.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:32
 * modify developer：  admin
 * modify time：10:32
 * modify remark：
 *
 * @version 2.0
 */
@Module
public class NetWorkModule {
//        Cache cache = new Cache(new File(),1024*1024*20);


    @Provides
    @ApplicationScope
    RadioApi provideReadApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(userInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.host)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(RadioApi.class);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
//                .cache(new Cache(new File(""),1024*1024*20))
                .build().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }

}
