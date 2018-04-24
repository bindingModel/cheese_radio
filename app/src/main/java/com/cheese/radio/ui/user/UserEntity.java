package com.cheese.radio.ui.user;

import android.text.TextUtils;

import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {

    private String token;
    private String nickname;
    private String sex;
    private String age;
    private String birthday;
    private Boolean canBookCheck;
    private String mobile;
    private String userId;

    public UserEntity clone(UserEntity entity) {

        if (entity == null) return this;
        token = entity.getToken();

        for(Field field:entity.getClass().getDeclaredFields()){
            String name=field.getName();
            String getValue=name.substring(0,1).toUpperCase()+name.substring(1);
            try {
                Method get=entity.getClass().getMethod("get"+getValue);
                Method set=entity.getClass().getMethod("set"+getValue, field.getType());
               set.invoke(this,get.invoke(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        for (Field field : ReflectUtil.getAllFields(entity.getClass())) {
//            Field entityField = ReflectUtil.getField(field.getName(), getClass());
//            if (entityField == null) continue;
//            Object value = ReflectUtil.beanGetValue(entityField, entity);
//            if (value != null && !TextUtils.isEmpty(value.toString()))
//                ReflectUtil.beanSetValue(field, this, value);
//        }
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        if(!TextUtils.isEmpty(nickname)){
            return nickname;
        }else if(!TextUtils.isEmpty(token)){
            if(token.length()>=20)
                return nickname = token.substring(0, 20);
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
        if (TextUtils.isEmpty(birthday))
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
}
