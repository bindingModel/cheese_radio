package com.cheese.radio.inject.api;

import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.ui.home.page.RecommanData;
import com.cheese.radio.ui.home.page.HomePageParams;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.media.anchor.AnchorData;
import com.cheese.radio.ui.media.anchor.AnchorParams;
import com.cheese.radio.ui.media.anchors.AnchorsItem;
import com.cheese.radio.ui.media.anchors.AnchorsParams;
import com.cheese.radio.ui.media.classify.ClassifyData;
import com.cheese.radio.ui.media.classify.ClassifyParams;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.media.play.PlayParams;
import com.cheese.radio.ui.user.demo.DemoData;
import com.cheese.radio.ui.user.login.entity.PlatformEntity;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.cheese.radio.ui.user.login.entity.SignUserEntity;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by arvin on 2017/11/28.
 */

public interface RadioApi {
    String host = "http://111.231.237.11:8081";

    @POST("/1.0/author")
    Observable<InfoEntity<List<AnchorsItem>>> getAnchors(@Body AnchorsParams params);

    @POST("/1.0/common")
    Observable<InfoEntity<Object>> getSMS(@Body SmsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<SignUserEntity>> getToken (@Body SignParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<CategoryEntity>>> getCategoriy (@Body HomePageParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<RecommanData>>> getRecommand (@Body HomePageParams params);
    @POST("data")
    Observable<InfoEntity<DemoData>> getData();

    @POST("/1.0/author")
    Observable<InfoEntity<AnchorData>> getAuthor(@Body AnchorParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<ClassifyData>>> getQueryCategroy(@Body ClassifyParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<CategoryEntity>>> getGroupInfo(@Body PlayParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<PlayEntity>> getContentInfo(@Body PlayParams params);

    @POST("/1.0/common")
    Observable<InfoEntity<PlatformEntity>> getOpenPlatformConfig(@Body PlatformParams params);


}
