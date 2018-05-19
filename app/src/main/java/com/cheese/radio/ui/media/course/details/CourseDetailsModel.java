package com.cheese.radio.ui.media.course.details;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.model.inter.Event;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCourseDetailsBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/4/1.
 */
@ModelView(R.layout.activity_course_details)
public class CourseDetailsModel extends ViewHttpModel<CourseDetailsActivity, ActivityCourseDetailsBinding, CourseDetailsData> {
    @Inject
    CourseDetailsModel() {
    }


    @Override
    public void accept(CourseDetailsData courseDetailsData) throws Exception {
        getDataBinding().setEntity(courseDetailsData);
//        MyBaseUtil.setWebView(getDataBinding().webview,courseDetailsData.getClassDesc());
        MyBaseUtil.setWebView(getDataBinding().webview, courseDetailsData.getClassDesc());
    }


    private CourseDetailsParams params = new CourseDetailsParams("classInfo");
    private Integer classId;
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, CourseDetailsActivity courseDetailsActivity) {
        super.attachView(savedInstanceState, courseDetailsActivity);
        classId = getT().getIntent().getIntExtra(Constant.classId, 0);
        if (classId != 0) {
            params.setClassId(classId);
            setRcHttp((offset1, refresh) -> api.getClassInfo(params).compose(new RestfulTransformer<>()));
        }

    }

    public void onEnrollClick(View view) {
        params.setMethod("bookClass");
        addDisposable(api.getBookClass(params).compose(new RestfulTransformer<>()).subscribe(
                s -> {
                    getDataBinding().enroll.setText("报名成功");
                    if (Event.event(R.id.calendarModer, this, view) == 1) {
                        getT().finish();
                    }
                }, BaseUtil::toast)
        );
    }
    public int getClassId(){
       return classId;
    }
}
