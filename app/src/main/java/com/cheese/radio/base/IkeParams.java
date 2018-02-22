package com.cheese.radio.base;

import com.binding.model.data.encrypt.FormUnionParams;

/**
 * Created by arvin on 2017/12/4.
 */

public class IkeParams extends FormUnionParams {
    @Override
    public String getKey() {
        return "";
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
