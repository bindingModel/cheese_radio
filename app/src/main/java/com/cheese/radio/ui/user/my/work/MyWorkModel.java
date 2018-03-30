package com.cheese.radio.ui.user.my.work;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMyWorkBinding;
import com.cheese.radio.inject.api.RadioApi;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_my_work)
public class MyWorkModel extends RecyclerModel<MyWorkActivity,ActivityMyWorkBinding,Inflate>{
    @Inject MyWorkModel(){}


    @Inject
    RadioApi api;
    @Override
    public void attachView(Bundle savedInstanceState, MyWorkActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().layoutRecycler.setVm(this);
        //我的作品 从哪个地方添加进来数据？？
        api.getMyWork(new MyWorkParams("myContents")).compose(new RestfulTransformer<>()).subscribe(list -> {});
    }
}
