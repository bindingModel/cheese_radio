package com.cheese.radio.inject.api;

import com.cheese.radio.base.IkeParams;
import com.cheese.radio.base.RadioParams;

public class ContentParams extends IkeParams {
    private String method;

    public ContentParams(String activity) {
        this.method =activity;
    }
    public ContentParams() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
