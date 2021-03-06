package com.cheese.radio.ui.media.classify;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/17.
 */

public class ClassifyParams extends IkeParams {
    private String method;
    private Integer id;

    public ClassifyParams(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
