package com.cheese.radio.ui.user.profile;

import android.widget.TextView;

import com.cheese.radio.base.IkeParams;
import com.cheese.radio.ui.user.UserEntity;

import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;
import static com.cheese.radio.util.MyBaseUtil.getNameError;

/**
 * Created by 29283 on 2018/3/25.
 */

public class ProfileParams extends IkeParams {
    //    method	方法名	是	固定	setProperty
//    token	用户令牌	可选	用户登录或注册后获取
//    nickName	昵称	输入
//    sex	性别	可选	用户选择
//    birthday	宝宝生日	可选	yyyy-MM-dd
    private String method;
    private String nickName;
    private String sex;
    private String birthday;

    public ProfileParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ProfileParams setMsg(UserEntity entity) {
        nickName = entity.getNickName();
        if (entity.getSex() != null) sex = entity.getSex();
        if (entity.getBirthday() != null) birthday = entity.getBirthday();
        return this;
    }

    public boolean isValidName(TextView view) {
        return isValidToast(view, getNameError(nickName));
    }
}
