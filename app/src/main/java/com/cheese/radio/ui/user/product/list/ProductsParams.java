package com.cheese.radio.ui.user.product.list;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/24.
 */

public class ProductsParams extends IkeParams {
//
//    method	方法名	是	固定	getProducts
//    token	用户令牌	是	用户登录或注册后获取
    private String method;

    public ProductsParams(String method) {
        this.method = method;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
