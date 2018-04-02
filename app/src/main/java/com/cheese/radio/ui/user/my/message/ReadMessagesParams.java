package com.cheese.radio.ui.user.my.message;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/2.
 */

public class ReadMessagesParams extends IkeParams {
//    method	方法名	是	固定	readMessages
//    id	消息ID	是	固定	readMessages
//    token	用户令牌	可选	用户登录或注册后获取
        private String method;
        private int id ;

    public ReadMessagesParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
