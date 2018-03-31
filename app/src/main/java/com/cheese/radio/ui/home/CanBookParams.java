package com.cheese.radio.ui.home;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/31.
 */

public class CanBookParams extends IkeParams {
    private String method;

    public CanBookParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
