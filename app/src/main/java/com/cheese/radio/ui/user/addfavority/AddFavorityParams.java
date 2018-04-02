package com.cheese.radio.ui.user.addfavority;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/1.
 */

public class AddFavorityParams extends IkeParams {
//    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	addFavority
//    id	绘本ID	是	固定	11
//    token	用户令牌	是	用户登录或注册后获取
    private String method ;
    private int id;

    public AddFavorityParams(String method) {
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
