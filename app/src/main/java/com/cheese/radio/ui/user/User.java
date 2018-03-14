package com.cheese.radio.ui.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.binding.model.data.save.SharePreferenceUtil;

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
        util.setValue("token",token);
    }
}
