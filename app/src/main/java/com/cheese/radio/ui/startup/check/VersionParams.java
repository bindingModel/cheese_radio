package com.cheese.radio.ui.startup.check;

import com.cheese.radio.base.IkeParams;

public class VersionParams extends IkeParams {
    private String method;
    private String os;
    private String version;

    public VersionParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
