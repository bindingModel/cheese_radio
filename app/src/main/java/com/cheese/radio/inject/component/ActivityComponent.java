package com.cheese.radio.inject.component;


import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.inject.scope.ActivityScope;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.anchors.AnchorsActivity;
import com.cheese.radio.ui.enroll.EnrollActivity;
import com.cheese.radio.ui.enroll.EnrollModel;
import com.cheese.radio.ui.guide.GuideActivity;
import com.cheese.radio.ui.home.HomeActivity;
import com.cheese.radio.ui.login.LoginActivity;
import com.cheese.radio.ui.startup.StartUpActivity;
import com.cheese.radio.ui.user.profile.ProfileActivity;

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
@Component(dependencies = AppComponent.class,modules={ActivityModule.class})
public interface ActivityComponent {
    void inject(HomeActivity activity);
    void inject(LoginActivity activity);
    void inject(StartUpActivity activity);
    void inject(GuideActivity activity);
    void inject(AnchorsActivity activity);
    void inject(ProfileActivity activity);
    void inject(EnrollActivity activity);
    interface Router {
        String cheese="/cheese/";
        String startup=cheese+"startup";
        String home = cheese +"home";
        String login=cheese+"login";
        String guide=cheese+"guide";
        String anchors=cheese+"anchors";
        String profile=cheese+"profile";
        String enroll=cheese+"enroll";
        String name=cheese+"name";
    }
}
