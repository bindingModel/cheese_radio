package com.cheese.radio.inject.interceptor;


import android.content.Context;

import com.binding.model.util.BaseUtil;
import com.cheese.radio.inject.qualifier.context.AppContext;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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

public class NbtvInterceptor implements Interceptor {
    private Context context;

    @Inject
    NbtvInterceptor(@AppContext Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
            boolean isConnection = BaseUtil.isNetworkConnected(context);
            if (!isConnection) {
                request = request.newBuilder()
                          .cacheControl(CacheControl.FORCE_CACHE)
                          .addHeader("User-Agent", "android")
                          .build();
            }
            Response response = chain.proceed(request);
            if (isConnection) {
                int maxAge = 60 * 60; // ike from cache for 60 minute
                response.newBuilder()
                          .removeHeader("Pragma")
                          .header("Cache-Control", "public, max-age=" + maxAge)
                          .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7; // tolerate 4-weeks stale
                response.newBuilder()
                          .removeHeader("Pragma")
                          .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                          .build();
            }
            return response;
        } catch (Exception e) {
            return new Response.Builder().code(-1).message("error").build();
        }
    }
}
