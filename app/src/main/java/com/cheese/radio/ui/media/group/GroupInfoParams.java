package com.cheese.radio.ui.media.group;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/21.
 */

public class GroupInfoParams extends IkeParams {
//    method	方法名	是	固定	groupInfo
//    id	专辑ID	是	固定	123
//    token	用户令牌	可选	用户登录或注册后获取
    private String method ;
    private String id;

    public GroupInfoParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
