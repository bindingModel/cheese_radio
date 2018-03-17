package com.cheese.radio.ui.media.anchor;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/3/16.
 */

public class AnchorParams extends IkeParams {
    private String method;
    private int authorId;

    public AnchorParams(String method, int authorId) {
        this.method = method;
        this.authorId = authorId;
    }

    public AnchorParams() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
