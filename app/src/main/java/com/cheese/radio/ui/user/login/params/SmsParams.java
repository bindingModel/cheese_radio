package com.cheese.radio.ui.user.login.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/14.
 */

public class SmsParams extends IkeParams {

    private String method;

    private String phone;

    private String validType;

    public SmsParams(String method, String validType) {
        this.method = method;
        this.validType = validType;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getPhone() {
        return phone;
    }

    public SmsParams setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
