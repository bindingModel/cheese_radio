package com.cheese.radio.ui.user.login.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 29283 on 2018/3/14.
 */

public class SignUserEntity {

    /**
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSJ9.ltzBvY5PtetpHtDHS1C_PhqO9__l54zbmki5aTYGiIF-OFFjuOQ7b4RbSWX_nQS69UQSbVIW371c5f57LLxx6w
     */

    private String token;
    /**
     * new : 0
     */

    @SerializedName("new")
    private int newX;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }
}
