package com.cheese.radio.ui.user.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/13.
 */

public class FabulousParams extends IkeParams {
//    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	addFabulous
//    token	用户令牌	可选	用户登录或注册后获取
//    id	绘本id	是	固定	11
        private String method;
        private Integer id;

    public FabulousParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
