package com.cheese.radio.ui.user.register;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/7.
 */

public class UserInfoParams extends IkeParams {
    private String method;
    private String sex;
    private String age;

    public UserInfoParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
