package com.cheese.radio.ui.user;

import android.text.TextUtils;

import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {

    private String token;
    private String nickName;
    private String sex;
    private String birthday;

    public UserEntity clone(UserEntity entity) {
        token = entity.getToken();
        UserEntity user = entity;
        if (user == null) return this;
        for (Field field : ReflectUtil.getAllFields(user.getClass())) {
            Field entityField = ReflectUtil.getField(field.getName(), getClass());
            if (entityField == null) continue;
            Object value = ReflectUtil.beanGetValue(entityField, user);
            if (value != null && !TextUtils.isEmpty(value.toString()))
                ReflectUtil.beanSetValue(field, this, value);
        }
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        if (nickName != null)
            return nickName;
        else {
            if (token != null) {

                return nickName = token.substring(0, 20);
            }
        }
        return "匿名用户";
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBabyBirth() {
        if (birthday != null)
            return "宝宝生日:" + birthday;
        else return "宝宝生日：暂未填写";
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(token);
    }
}