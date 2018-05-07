package com.cheese.radio.ui.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.binding.model.data.save.SharePreferenceUtil;
import com.binding.model.model.inter.Model;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.ui.user.profile.ProfileParams;
import com.cheese.radio.ui.user.register.UserInfoParams;

import java.util.Map;

import static com.cheese.radio.inject.component.ActivityComponent.Router.login;
import static com.cheese.radio.ui.Constant.logout;

/**
 * Created by 29283 on 2018/3/14.
 */

public class User {

    public static boolean isLogin = false;
    private boolean isFirst = true;
    private SharePreferenceUtil util;
    private final UserEntity userEntity;

    public User(Context context) {
        util = SharePreferenceUtil.getUserInstance(context);
        userEntity = util.getAllDto(UserEntity.class);
        isLogin = userEntity.isLogin();
    }

    public void setToken(String token) {
        userEntity.setToken(token);
        if (!TextUtils.isEmpty(token)) isLogin = true;
        util.setAllDto(userEntity);
    }

    public String getToken() {
        return userEntity.getToken();
    }

    public void setUserEntity(ProfileParams params) {
        if (params.getNickName() != null) userEntity.setNickName(params.getNickName());
        if (params.getBirthday() != null) userEntity.setBirthday(params.getBirthday());
        if (params.getSex() != null) userEntity.setSex(params.getSex());
        util.setAllDto(userEntity);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void logout() {
        userEntity.clone(new UserEntity());
        userEntity.getCount();
        util.setAllDto(userEntity);
        isLogin = userEntity.isLogin();
        ARouterUtil.navigation(login);
        Model.dispatchModel(logout);
    }

    public void setUserEntity(UserEntity entity) {
        String token = this.getToken();
        this.userEntity.clone(entity);
        this.userEntity.setToken(token);
        util.setAllDto(userEntity);
    }

    public Boolean getCanBookCheck() {
        return userEntity.getCanBookCheck();
    }

    public void setCanBookCheck(Boolean canBookCheck) {
        userEntity.setCanBookCheck(canBookCheck);
        util.setAllDto(userEntity);
    }

    //以下两个方法在用户初次登录后调用。
    public void setUserEntity(UserInfoParams params) {
        if (params.getAge() != null) userEntity.setAge(params.getAge());
        if (params.getSex() != null) userEntity.setSex(params.getSex());
        util.setAllDto(userEntity);
    }

    public UserInfoParams getUserInfoParams() {
        UserInfoParams params = new UserInfoParams("setUserInfo");
        params.setAge(userEntity.getAge());
        params.setSex(userEntity.getSex());
        return params;
    }

    public int checkIsFirstUse() {
        int count = userEntity.getCount();
        util.setAllDto(userEntity);
        return count;
    }
}
