package com.cheese.radio.ui.user.login.params;

import android.widget.TextView;

import com.cheese.radio.base.IkeParams;

import java.util.Map;

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
    private String otherinfo;
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public SignParams(String method) {
        this.method = method;
    }

    public SignParams(String method, String loginType) {
        this.method = method;
        this.loginType = loginType;
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

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public boolean isValidPhone(TextView view) {
        return isValidToast(view, getPhoneError(phone));
    }

    public boolean isValidSMS(TextView view){
        return isValidToast(view, getCodeError(validCode));
    }
}