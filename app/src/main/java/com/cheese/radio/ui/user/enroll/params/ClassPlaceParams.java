package com.cheese.radio.ui.user.enroll.params;

import com.cheese.radio.base.RadioParams;

/**
 * Created by 29283 on 2018/4/19.
 */

public class ClassPlaceParams extends RadioParams {
    private String method;

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
