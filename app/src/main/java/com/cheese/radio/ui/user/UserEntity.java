package com.cheese.radio.ui.user;

import android.text.TextUtils;

import java.lang.reflect.Field;

import static com.binding.model.util.ReflectUtil.beanGetValue;
import static com.binding.model.util.ReflectUtil.beanSetValue;
import static com.binding.model.util.ReflectUtil.getAllFields;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {
    private Integer count = 0;
    private String token;
    private String nickname;
    private String sex;
    private String age;
    private String birthday;
    private Boolean canBookCheck;
    private String mobile;
    private String userId;
    private String portrait;

    public void clone(UserEntity entity) {
        for (Field field : getAllFields(getClass())) {
            beanSetValue(field, this, beanGetValue(field, entity));
        }
//        apt
//        aop
//        ioc

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        if (!TextUtils.isEmpty(nickname)) {
            return nickname;
        } else if (!TextUtils.isEmpty(mobile)) {
            return nickname = mobile;
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
        this.nickname = nickName;
    }

    public String getSex() {
        return sex = (sex != null ? sex : "M");
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
        if (!TextUtils.isEmpty(birthday))
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getCount() {
        if (count == 0) {
            count = 1;
            return 0;
        }
        return 1;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
