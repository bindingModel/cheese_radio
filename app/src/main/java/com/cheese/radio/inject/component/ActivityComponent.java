package com.cheese.radio.inject.component;


import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.inject.scope.ActivityScope;
import com.cheese.radio.ui.demo.coordinatorLayout.CoordinatorLayoutActivity;
import com.cheese.radio.ui.media.anchor.AnchorActivity;
import com.cheese.radio.ui.media.anchors.AnchorsActivity;
import com.cheese.radio.ui.media.classify.ClassifyActivity;
import com.cheese.radio.ui.media.classify.list.ClassifyListActivity;
import com.cheese.radio.ui.media.course.details.CourseDetailsActivity;
import com.cheese.radio.ui.media.group.GroupInfoActivity;
import com.cheese.radio.ui.media.play.PlayActivity;
import com.cheese.radio.ui.search.SearchActivity;
import com.cheese.radio.ui.user.edit.EditNameActivity;
import com.cheese.radio.ui.user.enroll.EnrollActivity;

import com.cheese.radio.ui.user.guide.GuideActivity;
import com.cheese.radio.ui.home.HomeActivity;
import com.cheese.radio.ui.user.login.LoginActivity;
import com.cheese.radio.ui.startup.StartUpActivity;

import com.cheese.radio.ui.user.my.course.MyCourseActivity;
import com.cheese.radio.ui.user.my.favority.MyFavorityActivity;
import com.cheese.radio.ui.user.my.message.MessageActivity;
import com.cheese.radio.ui.user.my.message.details.DetailsActivity;
import com.cheese.radio.ui.user.my.work.MyWorkActivity;
import com.cheese.radio.ui.user.product.list.ProductsActivity;
import com.cheese.radio.ui.user.product.place.PlaceActivity;
import com.cheese.radio.ui.user.profile.ProfileActivity;
import com.cheese.radio.ui.user.register.one.RegisterOneActivity;
import com.cheese.radio.ui.user.register.two.RegisterTwoActivity;
import com.cheese.radio.ui.user.service.center.CenterActivity;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:28
 * modify developer：  admin
 * modify time：14:28
 * modify remark：
 *
 * @version 2.0
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(HomeActivity activity);

    void inject(LoginActivity activity);

    void inject(StartUpActivity activity);

    void inject(GuideActivity activity);

    void inject(AnchorsActivity activity);

    void inject(ProfileActivity activity);

    void inject(EnrollActivity activity);

    void inject(AnchorActivity activity);

    void inject(ClassifyActivity activity);

    void inject(PlayActivity activity);

    void inject(RegisterOneActivity activity);

    void inject(RegisterTwoActivity activity);

    void inject(GroupInfoActivity activity);

    void inject(SearchActivity activity);

    void inject(ClassifyListActivity activity);

    void inject(ProductsActivity activity);

    void inject(EditNameActivity activity);

    //    void inject(CalendarActivity activity);
    void inject(CenterActivity activity);

    void inject(MyCourseActivity activity);

    void inject(MyWorkActivity activity);

    void inject(MyFavorityActivity activity);

    void inject(MessageActivity activity);

    void inject(DetailsActivity activity);

    void inject(CourseDetailsActivity activity);

    void inject(CoordinatorLayoutActivity activity);
    void inject(PlaceActivity activity);
    interface Router {
        String cheese = "/cheese/";
        String startup = cheese + "startup";
        String home = cheese + "home";
        String login = cheese + "login";
        String guide = cheese + "guide";
        String authors = cheese + "authors";
        String profile = cheese + "profile";
        String enroll = cheese + "enroll";
        String name = cheese + "name";
        String author = cheese + "author";
        String categorys = cheese + "categorys";
        String play = cheese + "play";
        String registerOne = cheese + "registerOne";
        String registerTwo = cheese + "registerTwo";
        String group = cheese + "group";
        String search = cheese + "search";
        String contents = cheese + "contents";
        String products = cheese + "products";
        String product = cheese + "product";

        //        String calendar = cheese + "calendar";
        String center = cheese + "center";
        String course = cheese + "course";
        String work = cheese + "work";
        String favority = cheese + "favority";
        String message = cheese + "message";
        String detail = cheese + "detail";
        String coursedetails = cheese + "coursedetails";
        String demo = "demo";
        String place = cheese + "place";
    }
    /*
     *  CONTENT_LIST	绘本列表                  contents
        CATEGORY_LIST	分类列表                  categorys
        AUTHOR_LIST	    主播列表                  authors
        AUTHOR_INFO 	主播详情	                 author
        GROUP_INFO	    专辑详情	                 group
        PLAY	        播放器	                 play
     */
}
