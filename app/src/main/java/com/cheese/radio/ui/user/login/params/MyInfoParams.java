package com.cheese.radio.ui.user.login.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/20.
 */

public class MyInfoParams extends IkeParams {

    private String method;
    private String token;

    public MyInfoParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
