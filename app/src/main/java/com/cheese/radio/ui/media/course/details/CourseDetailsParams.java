package com.cheese.radio.ui.media.course.details;

import com.cheese.radio.base.IkeParams;

/**
 * Created by 29283 on 2018/4/1.
 */

public class CourseDetailsParams extends IkeParams {

//    参数名	参数含义	是否必填	生成规则	样例
//    method	方法名	是	固定	classInfo
//    token	用户令牌	是	用户登录或注册后获取
//    classId	课程ID	是	用户选择	123
    private String method ;
    private Integer classId;

    public CourseDetailsParams(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
