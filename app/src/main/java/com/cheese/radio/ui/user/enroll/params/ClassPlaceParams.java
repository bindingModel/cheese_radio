package com.cheese.radio.ui.user.enroll.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/19.
 */

public class ClassPlaceParams extends IkeParams {
    private String method;
//method:classPlace
    public ClassPlaceParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
//token必须
}
