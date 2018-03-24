package com.cheese.radio.ui.media.play;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/17.
 */

public class PlayParams extends IkeParams {
    private String method;
    private int id;

    public PlayParams(String method, int id) {
        this.method = method;
        this.id = id;
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
