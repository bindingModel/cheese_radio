package com.cheese.radio.ui.user.login.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/19.
 */

public class PlatformParams extends IkeParams {
    private String method;
    private String platform;

    public PlatformParams(String method) {
        this.method = method;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
