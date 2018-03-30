package com.cheese.radio.ui.user.my.course;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/30.
 */

public class MyCourseParams extends IkeParams {
//    method	方法名	是	固定	myClass
//    token	用户令牌	可选	用户登录或注册后获取
    private String method ;

    public MyCourseParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
