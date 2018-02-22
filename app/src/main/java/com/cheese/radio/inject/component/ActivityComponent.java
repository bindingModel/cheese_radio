package com.cheese.radio.inject.component;


import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.inject.scope.ActivityScope;
import com.cheese.radio.ui.Constant;

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


    interface Router {
        String ike = "/ike/";
        String user = "/"+Constant.user+"/";
        String gateWay = "/"+ Constant.gateWay+"/";
        String room = "/"+Constant.room+"/";
        String device = "/"+Constant.device+"/";

        String login = ike +"login";
        String home = ike +"home";
        String register = ike +"register";
        String password_forget = ike+"forget";
        String startup = ike +"startup";

        String wifi = user+"wifi";
        String account= user+"account";
        String senior_setting = user+"senior/setting";
        String password_modify = user+"password";

        String route = gateWay+"route";
        String room_add = gateWay +"room/add";

        String sight_add = room+"sight/add";
        String device_add = room+"light/add";
        String device_list = room+"/list";

        String sight_senior = device+"sight/senior";
        String sight_config = device+"sight/config";
        String light_setting = device+"light/setting";
    }
}
