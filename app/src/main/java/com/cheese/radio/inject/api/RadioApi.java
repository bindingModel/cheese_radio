package com.cheese.radio.inject.api;

import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.ui.home.CanBookParams;
import com.cheese.radio.ui.home.CanBookData;
import com.cheese.radio.ui.home.page.RecommanData;
import com.cheese.radio.ui.home.page.HomePageParams;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.media.anchor.AnchorData;
import com.cheese.radio.ui.media.anchor.AnchorParams;
import com.cheese.radio.ui.media.anchors.AnchorsItem;
import com.cheese.radio.ui.media.anchors.AnchorsParams;
import com.cheese.radio.ui.media.classify.ClassifyData;
import com.cheese.radio.ui.media.classify.ClassifyParams;
import com.cheese.radio.ui.media.classify.list.ClassifyListParams;
import com.cheese.radio.ui.media.course.details.CourseDetailsData;
import com.cheese.radio.ui.media.course.details.CourseDetailsParams;
import com.cheese.radio.ui.media.group.GroupInfoParams;
import com.cheese.radio.ui.media.group.fragment.GroupData;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.media.play.PlayParams;
import com.cheese.radio.ui.search.entity.HotSearchEntity;
import com.cheese.radio.ui.search.params.HotSearchParams;
import com.cheese.radio.ui.user.UserEntity;
import com.cheese.radio.ui.user.enroll.params.ClassPlaceParams;
import com.cheese.radio.ui.user.enroll.params.CreateOrderParams;
import com.cheese.radio.ui.user.params.AddFavorityParams;
import com.cheese.radio.ui.user.calendar.CalendarEntity;
import com.cheese.radio.ui.user.calendar.ClassCalendarParams;
import com.cheese.radio.ui.user.demo.DemoData;
import com.cheese.radio.ui.user.login.entity.PlatformEntity;
import com.cheese.radio.ui.user.login.params.MyInfoParams;

import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.cheese.radio.ui.user.login.entity.SignUserEntity;
import com.cheese.radio.ui.user.my.course.MyCourseData;
import com.cheese.radio.ui.user.my.course.MyCourseParams;
import com.cheese.radio.ui.user.my.favority.MyFavorityData;
import com.cheese.radio.ui.user.my.favority.MyFavorityParams;
import com.cheese.radio.ui.user.my.message.MessagesData;
import com.cheese.radio.ui.user.my.message.MessagesParams;
import com.cheese.radio.ui.user.my.message.ReadMessagesParams;
import com.cheese.radio.ui.user.my.push.NewMessageCountData;
import com.cheese.radio.ui.user.my.push.NewMessageCountParams;
import com.cheese.radio.ui.user.my.work.MyWorkEntity;
import com.cheese.radio.ui.user.my.work.MyWorkParams;
import com.cheese.radio.ui.user.params.FabulousParams;
import com.cheese.radio.ui.user.product.list.ProductsEntity;
import com.cheese.radio.ui.user.product.list.ProductsParams;
import com.cheese.radio.ui.user.product.place.ClassPlaceEntity;
import com.cheese.radio.ui.user.profile.ProfileParams;
import com.cheese.radio.ui.user.register.UserInfoParams;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
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
    Observable<InfoEntity<GroupData>> getGroupInfo(@Body GroupInfoParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<PlayEntity>> getContentInfo(@Body PlayParams params);

    //第三方登录APPKEY
    @POST("/1.0/common")
    Observable<InfoEntity<PlatformEntity>> getOpenPlatformConfig(@Body PlatformParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<Object>> getMyinfo(@Body MyInfoParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<HotSearchEntity>>> getHotSearch(
            @Body HotSearchParams params);
    @POST("/1.0/content")
    Observable<InfoEntity<MyFavorityData>> getSearch(@Body HotSearchParams params);
    //绘本列表 签名验证错误？
    @POST("/1.0/content")
    Observable<InfoEntity<MyFavorityData>> getQueryByTag(@Body ClassifyListParams params);

    @POST("/1.0/order")
    Observable<InfoEntity<List<ProductsEntity>>> getProducts(@Body ProductsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> setProperty(@Body ProfileParams params);

//    @POST("/1.0/user")
//    Observable<InfoEntity<String>> getProperty(@Body ProfileParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<MyCourseData>> getMyCourse(@Body MyCourseParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<List<MyWorkEntity>>> getMyWork(@Body MyWorkParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<MyFavorityData>> getMyFavority(@Body MyFavorityParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<NewMessageCountData>> getNewMessageCount(@Body NewMessageCountParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<MessagesData>> getMessages(@Body MessagesParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<List<CalendarEntity>>> getClassCalendar(@Body ClassCalendarParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<CanBookData>> getCanBook(@Body CanBookParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<CourseDetailsData>> getClassInfo(@Body CourseDetailsParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<String>> getBookClass(@Body CourseDetailsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> addFavority(@Body AddFavorityParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> readMessages(@Body ReadMessagesParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> setUserInfo(@Body UserInfoParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<UserEntity>> getUserInfo(@Body UserInfoParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> addFabulous(@Body FabulousParams params);

    //上课地址接口
    @POST("/1.0/class")
    Observable<InfoEntity<List<ClassPlaceEntity>>> classPlace(@Body ClassPlaceParams params);

    //创建订单
    @POST("/1.0/order")
    Observable<InfoEntity<String>> createOrder(@Body CreateOrderParams params);
}
