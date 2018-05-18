package com.cheese.radio.ui.startup.check;

public class VersionEntity {
//      "update":1,
//              "message":"新特性"
    private int update;
    private String message;

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
