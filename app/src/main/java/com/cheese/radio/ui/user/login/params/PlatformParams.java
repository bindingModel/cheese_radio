package com.cheese.radio.ui.user.login.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/19.
 */

public class PlatformParams extends IkeParams {
    private String openPlatformConfig;
    private String method;

    public PlatformParams(String method) {
        this.method = method;
    }

    public String getOpenPlatformConfig() {
        return openPlatformConfig;
    }

    public void setOpenPlatformConfig(String openPlatformConfig) {
        this.openPlatformConfig = openPlatformConfig;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
