package com.cheese.radio.ui.demo.coordinatorLayout;

import com.cheese.radio.base.IkeFileParams;

import java.io.File;

public class DemoParams extends IkeFileParams {
    private File info;
    private String method;

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
