package com.cheese.radio.inject.api;

import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.ui.home.CanBookData;
import com.cheese.radio.ui.home.CanBookParams;
import com.cheese.radio.ui.home.circle.CircleDateEntity;
import com.cheese.radio.ui.home.circle.DateDetailParams;
import com.cheese.radio.ui.home.circle.join.JoinCircleDetailParams;
import com.cheese.radio.ui.home.clock.ClockEnrollEntity;
import com.cheese.radio.ui.home.clock.CourseTypeInfoEntity;
import com.cheese.radio.ui.home.page.HomePageParams;
import com.cheese.radio.ui.home.page.RecommanData;
import com.cheese.radio.ui.home.page.banner.HomePageBannerEntity;
import com.cheese.radio.ui.home.page.banner.HomePageBannerTimeEntity;
import com.cheese.radio.ui.home.page.banner.HomePageBannerTimeParams;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.home.page.entity.RecommandEntity;
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
import com.cheese.radio.ui.media.play.FabuEntity;
import com.cheese.radio.ui.media.play.FavorEntity;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.media.play.PlayInOrderParams;
import com.cheese.radio.ui.media.play.PlayParams;
import com.cheese.radio.ui.media.play.PlayRecordParams;
import com.cheese.radio.ui.search.entity.HotSearchEntity;
import com.cheese.radio.ui.search.params.HotSearchParams;
import com.cheese.radio.ui.startup.check.VersionEntity;
import com.cheese.radio.ui.startup.check.VersionParams;
import com.cheese.radio.ui.user.UserEntity;
import com.cheese.radio.ui.user.calendar.CalendarEntity;
import com.cheese.radio.ui.user.calendar.CancelBookParams;
import com.cheese.radio.ui.user.calendar.ClassCalendarParams;
import com.cheese.radio.ui.user.demo.DemoData;
import com.cheese.radio.ui.user.enroll.AliEntity;
import com.cheese.radio.ui.user.enroll.CreateOrderWXEntity;
import com.cheese.radio.ui.user.enroll.params.ClassPlaceParams;
import com.cheese.radio.ui.user.enroll.params.CreateOrderParams;
import com.cheese.radio.ui.user.login.entity.PlatformEntity;
import com.cheese.radio.ui.user.login.entity.SignUserEntity;
import com.cheese.radio.ui.user.login.params.MyInfoParams;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.cheese.radio.ui.user.my.course.MyCourseData;
import com.cheese.radio.ui.user.my.course.MyCourseEntity;
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
import com.cheese.radio.ui.user.params.AddFavorityParams;
import com.cheese.radio.ui.user.params.FabulousParams;
import com.cheese.radio.ui.user.phone.BindPhoneParams;
import com.cheese.radio.ui.user.product.list.ProductsEntity;
import com.cheese.radio.ui.user.product.list.ProductsParams;
import com.cheese.radio.ui.user.product.place.ClassPlaceEntity;
import com.cheese.radio.ui.user.profile.MyHeadData;
import com.cheese.radio.ui.user.profile.MyHeadParams;
import com.cheese.radio.ui.user.profile.ProfileParams;
import com.cheese.radio.ui.user.register.UserInfoParams;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by arvin on 2017/11/28.
 */

public interface RadioApi {
//    String host = "http://111.231.237.11:8081";
//    String htmlHost = BuildConfig.html+ "";

    @POST("/1.0/author")
    Observable<InfoEntity<List<AnchorsItem>>> getAnchors(@Body AnchorsParams params);

    @POST("/1.0/common")
    Observable<InfoEntity<Object>> getSMS(@Body SmsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<SignUserEntity>> getToken(@Body SignParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<CategoryEntity>>> getCategoriy(@Body HomePageParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<RecommanData>>> getRecommand(@Body HomePageParams params);

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
    Observable<InfoEntity<List<RecommandEntity>>> getHotSearch(@Body HotSearchParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<MyFavorityData>> getSearch(@Body HotSearchParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<MyFavorityData>> getQueryByTag(@Body ClassifyListParams params);

    @POST("/1.0/order")
    Observable<InfoEntity<List<ProductsEntity>>> getProducts(@Body ProductsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> setProperty(@Body ProfileParams params);

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
    Observable<InfoEntity<CalendarEntity>> getBookClass(@Body CourseDetailsParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<FavorEntity>> addFavority(@Body AddFavorityParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<Object>> readMessages(@Body ReadMessagesParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<String>> setUserInfo(@Body UserInfoParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<UserEntity>> getUserInfo(@Body UserInfoParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<FabuEntity>> addFabulous(@Body FabulousParams params);

    //上课地址接口
    @POST("/1.0/class")
    Observable<InfoEntity<List<ClassPlaceEntity>>> classPlace(@Body ClassPlaceParams params);

    //创建订单 wx
    @POST("/1.0/order")
    Observable<InfoEntity<CreateOrderWXEntity>> createWXOrder(@Body CreateOrderParams params);

    @POST("/1.0/order")
    Observable<InfoEntity<AliEntity>> createAliOrder(@Body CreateOrderParams params);

    //    修改头像
    @POST("/1.0/file")
    Observable<InfoEntity<MyHeadData>> myHead(@Body MyHeadParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<Object>> playRecord(@Body PlayRecordParams params);

    @POST("/1.0/user")
    Observable<InfoEntity<Object>> bindPhone(@Body BindPhoneParams params);

    @POST("/1.0/common")
    Observable<InfoEntity<VersionEntity>> version(@Body VersionParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<String>> cancelBook(@Body CancelBookParams params);

    //------------二期
//    活动列表接口：/1.0/content?method=activity
    @POST("/1.0/content")
    Observable<InfoEntity<List<CircleDateEntity>>> getCircleDateList(@Body ContentParams params);
    @POST("/1.0/content")
    Observable<InfoEntity<CircleDateEntity>> getCircleDateDetail(@Body DateDetailParams params);
    @POST("/1.0/content")
    Observable<InfoEntity<String>> circleDatetailEnroll(@Body JoinCircleDetailParams params);

    //随机播放
    @POST("/1.0/content")
    Observable<InfoEntity<PlayEntity>> playInOrder(@Body PlayInOrderParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<List<ClockEnrollEntity>>> courseTypeList(@Body ContentParams params);

    @POST("/1.0/class")
    Observable<InfoEntity<CourseTypeInfoEntity>> courseTypeInfo(@Body DateDetailParams params);
    @POST("/1.0/user")
    Observable<InfoEntity<List<MyCourseEntity>>> myClass2(@Body MyCourseParams params);

    //报名，获取商品列表
    @POST("1.0/order")
    Observable<InfoEntity<List<ProductsEntity>>> getProduct2(@Body ProductsParams ProductsParams);

    @POST("1.0/user")
    Observable<InfoEntity<List<MyCourseEntity>>> myClassInfo(@Body MyCourseParams params);

    @POST("/1.0/content")
    Observable<InfoEntity<List<HomePageBannerEntity>>> getBanner(@Body HomePageParams setAreaSelf);

    @POST("/1.0/common")
    Observable<InfoEntity<HomePageBannerTimeEntity>> getBannerTime(@Body HomePageBannerTimeParams setAreaSelf);
}
