package com.cheese.radio.inject.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：22:40
 * modify developer：  admin
 * modify time：22:40
 * modify remark：
 *
 * @version 2.0
 */


public class CheckTokenInterceptor implements Interceptor {

     CheckTokenInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {

//                if (MyApplication.instance().isLogin()) {
//                    MyApplication.instance().logout();
//                    RxBus.getDefault().post(new BaseApplication.Message(401, "token dissmiss!!!!"));
//                    Routers.open(MyApplication.instance(), "tonghang://login");
//                }
            return new Response.Builder().code(401).message("token dissmiss!!!!").build();
        } else {
            return response;
        }
    }
}
