package com.cheese.radio.ui.media.classify.list;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/24.
 */

public class ClassifyListParams extends IkeParams {
//    method	方法名	是	固定	queryByTag
//    tagId	标签ID	是	固定	123
//    filter	过滤	可选	固定	返回内容过滤，如果不传返回所有，如果设置此参数则返回对应的内容：group｜single
//    startIndex	起始行数	可选	客户端设置，0开始	0
//    maxCount	最大返回记录数	可选	客户端设置	8
//    token	用户令牌	可选	用户登录或注册后获取
    private String method;
    private int tagId;
    private String filter;
    private String startIndex;
    private String maxCount;

    public ClassifyListParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }
}
