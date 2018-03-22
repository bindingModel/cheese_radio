package com.cheese.radio.inject.component;


import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.inject.scope.ActivityScope;
import com.cheese.radio.ui.media.anchor.AnchorActivity;
import com.cheese.radio.ui.media.anchors.AnchorsActivity;
import com.cheese.radio.ui.media.classify.ClassifyActivity;
import com.cheese.radio.ui.media.group.GroupInfoActivity;
import com.cheese.radio.ui.media.play.PlayActivity;
import com.cheese.radio.ui.search.SearchActivity;
import com.cheese.radio.ui.user.enroll.EnrollActivity;
import com.cheese.radio.ui.user.guide.GuideActivity;
import com.cheese.radio.ui.home.HomeActivity;
import com.cheese.radio.ui.user.login.LoginActivity;
import com.cheese.radio.ui.startup.StartUpActivity;
import com.cheese.radio.ui.user.profile.ProfileActivity;
import com.cheese.radio.ui.user.register.one.RegisterOneActivity;
import com.cheese.radio.ui.user.register.two.RegisterTwoActivity;

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

    interface Router {
        String cheese = "/cheese/";
        String startup = cheese + "startup";
        String home = cheese + "home";
        String login = cheese + "login";
        String guide = cheese + "guide";
        String anchors = cheese + "anchors";
        String profile = cheese + "profile";
        String enroll = cheese + "enroll";
        String name = cheese + "name";
        String anchor = cheese + "anchor";
        String classify = cheese + "classify";
        String play = cheese + "play";
        String registerOne = cheese + "registerOne";
        String registerTwo = cheese + "registerTwo";
        String groupInfo = cheese + "groupInfo";
        String search = cheese + "search";
    }
    /*
     *  CONTENT_LIST	绘本列表
        CATEGORY_LIST	分类列表
        AUTHOR_LIST	    主播列表                  anchors
        AUTHOR_INFO 	主播详情	                 anchor
        GROUP_INFO	    专辑详情	                 groupInfo
        PLAY	        播放器	                 play
     */
}
