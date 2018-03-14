package com.cheese.radio.ui.user.login;

import com.cheese.radio.util.CheeseApiParams;

/**
 * Created by 29283 on 2018/3/10.
 */

public class LoginParams extends CheeseApiParams {
    private String timestamp;
    private String sign;

    public String getTimestamp() {
        return timestamp= String.valueOf(System.currentTimeMillis()/1000);//获取系统时间的10位的时间戳;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
