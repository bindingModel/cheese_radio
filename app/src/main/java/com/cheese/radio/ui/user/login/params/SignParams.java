package com.cheese.radio.ui.user.login.params;

import android.widget.TextView;

import com.cheese.radio.base.IkeParams;

import static com.binding.model.util.BaseUtil.getCodeError;
import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;

/**
 * Created by 29283 on 2018/3/14.
 */

public class SignParams extends IkeParams {
    private String phone;
    private String validCode;
    private String method;
    private String loginType;
    private String openId;
    private String otherInfo;

    public SignParams(String method) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }


    public boolean isValidPhone(TextView view) {
        return isValidToast(view, getPhoneError(phone));
    }

    public boolean isValidSMS(TextView view){
        return isValidToast(view, getCodeError(validCode));
    }
}