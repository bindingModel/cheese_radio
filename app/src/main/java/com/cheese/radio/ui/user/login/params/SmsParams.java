package com.cheese.radio.ui.user.login.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/14.
 */

public class SmsParams extends IkeParams {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public SmsParams setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
