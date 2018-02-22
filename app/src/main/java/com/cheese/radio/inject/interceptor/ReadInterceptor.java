package com.cheese.radio.inject.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by apple on 2017/7/5.
 */

public class ReadInterceptor implements Interceptor {
//    private String token;
////    private Context context;
//
//    @Inject
//    public ReadInterceptor(@AppContext Context context) {
////        this.context = context;
//        token = SharePreferenceUtil.getNingInstance(context).getValue("token",String.class);
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Request.Builder builder = request.newBuilder();
//        builder.addHeader("token",token).build();
//
//        return chain.proceed(request);
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
