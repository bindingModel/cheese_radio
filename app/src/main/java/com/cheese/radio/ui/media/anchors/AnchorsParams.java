package com.cheese.radio.ui.media.anchors;

import com.cheese.radio.base.IkeParams;
import com.cheese.radio.util.CheeseApiParams;
import com.cheese.radio.util.MD5;
import com.cheese.radio.util.MyBaseUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 29283 on 2018/3/11.
 */

public class AnchorsParams extends IkeParams{
    private String method;

    public String getMethod() {
        return method="list";
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
