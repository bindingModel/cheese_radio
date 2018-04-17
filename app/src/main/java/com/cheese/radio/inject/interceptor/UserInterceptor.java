package com.cheese.radio.inject.interceptor;

import android.content.Context;
import android.support.annotation.Nullable;

import com.cheese.radio.inject.qualifier.context.AppContext;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:01
 * modify developer：  admin
 * modify time：10:01
 * modify remark：
 *
 * @version 2.0
 */

public class UserInterceptor implements Interceptor {
    private Context context;

    @Inject
    public UserInterceptor(@AppContext Context context) {
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        return chain.proceed(builder.build());
    }
}



//"Content-Type":"application/x-www-form-urlencoded ; charset=utf-8"
//        Request request = chain.request();
//        boolean isConnection = BaseUtil.isNetworkConnected(context);
//        String url = request.url().toString();
//        Request.Builder builder = request.newBuilder();
//        if (isConnection) {
//            builder.removeHeader("Pragma")
//                    .addHeader("User-Agent", "android");
////                    .build();
//        } else {
//            builder.cacheControl(CacheControl.FORCE_CACHE)
//                    .addHeader("User-Agent", "android");
//        }
//        if(!TextUtils.isEmpty(IkeApplication.getUser().getCookies())&&!url.contains("dologin"))
//            builder.addHeader("cookie", IkeApplication.getUser().getCookies());
//        request = builder.build();
//        Response response = chain.proceed(request);
//        if (url.contains("dologin")||url.contains("doRegister")) {
//            String cookie = response.header("cookie");
//            if(TextUtils.isEmpty(cookie)){
//                List<String> cookies = response.headers("Set-Cookie");
//                if(!cookies.isEmpty())cookie = cookies.get(0);
//            }
//            if(!TextUtils.isEmpty(cookie)&&cookie.startsWith("JSESSIONID")){
//                IkeApplication.getUser().setCookies(cookie.split(";")[0]);
//            } else {
//                return response;
////                        .newBuilder().code(401).message("请再登录一次").build();
//            }
//        }
//        if (response.code() == 401) {
//            return response.newBuilder().code(401).message("").build();
//        }
//        return response;