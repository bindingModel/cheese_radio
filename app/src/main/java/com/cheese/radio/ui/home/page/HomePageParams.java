package com.cheese.radio.ui.home.page;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/15.
 */

public class HomePageParams extends IkeParams {
        private String method;

    public HomePageParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
