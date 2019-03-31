package com.cheese.radio.ui.user.profile;

import com.cheese.radio.base.IkeFileParams;

import java.io.File;

public class MyHeadParams extends IkeFileParams{
    private File info;
    private String method;
//myHead
    public MyHeadParams(String method) {
        this.method = method;
    }

    public File getInfo() {
        return info;
    }

    public void setInfo(File info) {
        this.info = info;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
