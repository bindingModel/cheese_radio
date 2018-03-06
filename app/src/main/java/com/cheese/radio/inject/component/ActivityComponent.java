package com.cheese.radio.inject.component;


import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.inject.scope.ActivityScope;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.home.HomeActivity;

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

    interface Router {
        String cheese="/cheese/";
        String startup=cheese+"startup";
        String home = cheese +"home";
        String login=cheese+"login";
        String guide=cheese+"guide";
    }
}
