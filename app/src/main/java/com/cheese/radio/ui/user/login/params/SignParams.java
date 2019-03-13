package com.cheese.radio.ui.user.login.params;

import android.widget.TextView;

import com.binding.model.util.BaseUtil;
import com.cheese.radio.base.IkeParams;

import static com.binding.model.util.BaseUtil.getCodeError;
import static com.binding.model.util.BaseUtil.getPasswordError;
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
    private String openId2;
    private String password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getOpenId2() {
        return openId2;
    }

    public void setOpenId2(String openId2) {
        this.openId2 = openId2;
    }

    public boolean isValidPassword(TextView view) {
        return isValidToast(view,getPasswordError(password));
    }
}