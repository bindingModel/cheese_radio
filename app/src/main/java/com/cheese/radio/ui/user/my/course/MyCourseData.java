package com.cheese.radio.ui.user.my.course;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

import java.util.List;

/**
 * Created by 29283 on 2018/3/30.
 */

public class MyCourseData {

    /**
     * already : [{"classId":123,"teacherId":12,"teacherName":"麦麦","className":"课程名称","classImage":"http://xx/xx.jpg","time":"18:00-20:00","teacherIcon":"http://xx/xx.jpg","day":"2018-04-01","bookId":1}]
     * leftHour : 12
     */

    private int leftHour;

    private List<MyCourseEntity> already;

    public int getLeftHour() {
        return leftHour;
    }

    public void setLeftHour(int leftHour) {
        this.leftHour = leftHour;
    }

    public List<MyCourseEntity> getAlready() {
        return already;
    }

    public void setAlready(List<MyCourseEntity> already) {
        this.already = already;
    }


}

