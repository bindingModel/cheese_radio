package com.cheese.radio.ui.home.circle;

import com.cheese.radio.inject.api.ContentParams;

public class DateDetailParams extends ContentParams {

    public DateDetailParams() {
    }

    public DateDetailParams(String activity) {
        super(activity);
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
