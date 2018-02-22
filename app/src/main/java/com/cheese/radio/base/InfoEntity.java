package com.cheese.radio.base;

/**
 * Created by arvin on 2017/11/30.
 */

public class InfoEntity<T> {
    private String msg;
    private T data;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
