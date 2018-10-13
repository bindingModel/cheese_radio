package com.cheese.radio.ui.user.my.course;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMyCourseBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_my_course)
public class MyCourseModel extends RecyclerModel<MyCourseActivity, ActivityMyCourseBinding, MyCourseEntity> {
    @Inject
    MyCourseModel() {
    }

    @Inject
    RadioApi api;
    private int courseTypeId;
    private MyCourseParams myCourseParams = new MyCourseParams();

    @Override
    public void attachView(Bundle savedInstanceState, MyCourseActivity myCourseActivity) {
        super.attachView(savedInstanceState, myCourseActivity);
        getDataBinding().layoutRecycler.setVm(this);
        courseTypeId = myCourseActivity.getIntent().getIntExtra(Constant.courseTypeId, -1);
        if (courseTypeId == -1) {
            myCourseParams.setMethod("myClass2");
            setRcHttp(((offset1, refresh) -> api.myClass2(myCourseParams).compose(new RestfulTransformer<>())));
        } else{
            myCourseParams.setMethod("myClassInfo");
            myCourseParams.setCourseTypeId(courseTypeId);
            setRcHttp((offset1, refresh) -> api.myClassInfo(myCourseParams).compose(new RestfulTransformer<>()));
        }
//        api.getMyCourse(new MyCourseParams("myClass")).compose(new RestfulTransformer<>()).subscribe(list -> {
//           accept(list.getAlready());
//        });
    }
}
