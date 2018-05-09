package com.cheese.radio.ui.media.play;

import com.cheese.radio.base.IkeParams;

import java.lang.reflect.Method;

public class PlayRecordParams extends IkeParams {
//    接口参数：
//    method	playRecord	必须
//    id	11	返回信息中的fid字段的值
//    token	可选
    private int id;
    private String method;

    public PlayRecordParams(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
