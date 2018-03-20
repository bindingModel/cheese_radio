package com.cheese.radio.ui.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.binding.model.data.save.SharePreferenceUtil;
import com.binding.model.model.inter.Model;
import com.cheese.radio.base.arouter.ARouterUtil;

import static com.cheese.radio.inject.component.ActivityComponent.Router.login;

/**
 * Created by 29283 on 2018/3/14.
 */

public class User {

    public static boolean isLogin = false;

    private SharePreferenceUtil util;
    private final UserEntity userEntity;

    public User(Context context) {
        util = SharePreferenceUtil.getUserInstance(context);
        userEntity = util.getAllDto(UserEntity.class);
        isLogin = userEntity.isLogin();
    }

    public void setToken(String token) {
        userEntity.setToken(token);
        util.setAllDto(userEntity);
    }

    public String getToken() {
        return userEntity.getToken();
    }

    public void logout(){
        userEntity.clone(new UserEntity());
        util.setAllDto(userEntity);
        isLogin = userEntity.isLogin();
        ARouterUtil.navigation(login);
//        Model.dispatchModel(logout);
    }
}
