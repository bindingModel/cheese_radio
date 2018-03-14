package com.cheese.radio.base;

/**
 * Created by arvin on 2017/11/30.
 */

public class InfoEntity<T> {
    private String message;
    private T data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
