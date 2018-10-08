package com.cheese.radio.ui.user.my.course;

import com.cheese.radio.base.IkeParams;
import com.cheese.radio.inject.api.ContentParams;

/**
 * Created by 29283 on 2018/3/30.
 */

public class MyCourseParams extends ContentParams {
    //根据分类查询时需要此字段，不传或者传“”代表查询所有
    private String courseTypeId;
    public MyCourseParams(String method) {
        super(method);
    }

    public MyCourseParams() {
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
}
