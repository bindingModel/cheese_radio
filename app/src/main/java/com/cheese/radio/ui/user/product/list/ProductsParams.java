package com.cheese.radio.ui.user.product.list;

import com.cheese.radio.base.IkeParams;
import com.cheese.radio.inject.api.ContentParams;

/**
 * Created by 29283 on 2018/3/24.
 */

public class ProductsParams extends ContentParams {

    private int courseTypeId;

    public ProductsParams(String method) {
        super(method);
    }

    public ProductsParams() {
    }

    public int getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(int courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
}
