package com.cheese.radio.ui.user.my.message;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/31.
 */

public class MessagesParams extends IkeParams {
//    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	getMessages
//    token	用户令牌	可选	用户登录或注册后获取
    private String method;

    public MessagesParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
