package com.cheese.radio.ui.user;

import android.text.TextUtils;

/**
 * Created by 29283 on 2018/3/14.
 */

public class UserEntity {

    private String token;

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
