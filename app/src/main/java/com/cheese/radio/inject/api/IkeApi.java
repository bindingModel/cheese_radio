package com.cheese.radio.inject.api;

import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.ui.anchors.AnchorsItem;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by arvin on 2017/11/28.
 */

public interface IkeApi {
    String host = "http://111.231.237.11:8081";

    @POST("/1.0/author")
    Observable<InfoEntity<List<AnchorsItem>>> getAnchors();
//    String host = "https://www.xcore-tech.com/";
//    String imageHost="https://www.xcore-tech.com/paladin1/Static/images/portrait/";
//
//    @POST("paladin1/Passport/dologin")
//    Observable<InfoEntity<UserEntity>> login(@Body UserParams userParams);
//
//    @POST("paladin1/Passport/doRegister")
//    Observable<InfoEntity<UserEntity>> register(@Body RegisterParams params);
//
//    @POST("paladin1/Passport/waregisterbymobile")
//    Observable<InfoEntity<UserEntity>> registerMobile(@Body RegisterParams params);
//
//    @FormUrlEncoded
//    @POST("paladin1/User/wadownloadversion")
//    Observable<DataEntity> download(@Field("is_app") int i);
//
//    @POST("paladin1/User/wauploadversion")
//    Observable<InfoEntity<String>> upload(@Body DataParams dataParams);
//
//    @POST("paladin1/User")
//    Observable<InfoEntity<String>> modifyPassword(@Body PasswordParams params);
//
//    @POST("paladin1/Passport/password_forget")
//    Observable<InfoEntity<UserEntity>> passwordForget(RegisterParams params);
}
