package com.cheese.radio.inject.api;

import com.cheese.radio.base.IkeParams;

public class ContentParams extends IkeParams {
    private String method;

    public ContentParams(String method) {
        this.method =method;
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
