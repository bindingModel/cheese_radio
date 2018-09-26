package com.cheese.radio.ui.media.play;

import com.cheese.radio.inject.api.ContentParams;

/**
 * @author 29283
 * @create 2018/9/26.
 */
public class PlayInOrderParams extends ContentParams {
    public PlayInOrderParams(String method) {
        super(method);
    }
    public PlayInOrderParams() {
    }
    private String actionType;
    private int id;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
