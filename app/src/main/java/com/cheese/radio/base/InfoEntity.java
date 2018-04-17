package com.cheese.radio.base;

import android.text.TextUtils;

/**
 * Created by arvin on 2017/11/30.
 */

public class InfoEntity<T> {
    private String message;
    private T data;
    private String code;

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

    public String getCode() {
        return code;
    }

    public int code(){
        if(TextUtils.isEmpty(code)||!TextUtils.isDigitsOnly(code))return 1;
        return Integer.parseInt(code);
    }

    public void setCode(String code) {
        this.code = code;
    }
}
