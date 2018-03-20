package com.cheese.radio.ui.user;

import android.text.TextUtils;

import com.binding.model.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {

    private String token;

    public UserEntity clone(UserEntity entity) {
        token=entity.getToken();
        UserEntity user = entity;
        if(user == null)return this;
        for (Field field : ReflectUtil.getAllFields(user.getClass())) {
            Field entityField = ReflectUtil.getField(field.getName(),getClass());
            if(entityField == null)continue;
            Object value = ReflectUtil.beanGetValue(entityField,user);
            if(value!=null&& !TextUtils.isEmpty(value.toString()))ReflectUtil.beanSetValue(field,this,value);
        }
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogin(){
        return !TextUtils.isEmpty(token);
    }
}
