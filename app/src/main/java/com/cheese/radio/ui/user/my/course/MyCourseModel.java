package com.cheese.radio.ui.user.my.course;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMyCourseBinding;
import com.cheese.radio.inject.api.ContentParams;
import com.cheese.radio.inject.api.RadioApi;

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
    @Override
    public void attachView(Bundle savedInstanceState, MyCourseActivity myCourseActivity) {
        super.attachView(savedInstanceState, myCourseActivity);
        getDataBinding().layoutRecycler.setVm(this);
        setRcHttp(((offset1, refresh) -> api.myClassTwo(new MyCourseParams("myClass2")).compose(new RestfulTransformer<>())));
//        api.getMyCourse(new MyCourseParams("myClass")).compose(new RestfulTransformer<>()).subscribe(list -> {
//           accept(list.getAlready());
//        });
    }
}
