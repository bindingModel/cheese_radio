package com.cheese.radio.ui.user.phone;

import com.cheese.radio.base.IkeParams;

public class BindPhoneParams extends IkeParams {
    private String method ;
    private String phone;
    private String validCode;

    public BindPhoneParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
