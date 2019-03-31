package com.cheese.radio.ui.search.params;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/22.
 */

public class HotSearchParams extends IkeParams {


//    参数名	参数含义	是否必填	生成规则	样例
//    @method	方法名	是	固定	hotsearch
//    token	用户令牌	可选	用户登录或注册后获取	123
//    --------------------------------------------
//    method	方法名	是	固定	hotsearch
//    token	用户令牌	可选	用户登录或注册后获取	123
//    title	用户输入的信息	是	用户决定	小猪佩奇
//    startIndex	起始行数	可选	客户端设置，0开始	0
//    maxCount	最大返回记录数	可选	客户端设置	8
    private String method ;
    private String title;
    private int startIndex;
    private int maxCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public HotSearchParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
