package com.cheese.radio.ui.user;

import android.text.TextUtils;

import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {

    private String token;
    private String nickName;
    private String sex;
    private String age;
    private String birthday;
    private Boolean canBookCheck;
    private String mobile;
    private String userId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex = (sex != null ? sex : "F");
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

    public Boolean getCanBookCheck() {
        return canBookCheck;
    }

    public void setCanBookCheck(Boolean canBookCheck) {
        this.canBookCheck = canBookCheck;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
