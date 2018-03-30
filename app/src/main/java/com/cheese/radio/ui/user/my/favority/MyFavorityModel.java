package com.cheese.radio.ui.user.my.favority;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMyFavorityBinding;
import com.cheese.radio.inject.api.RadioApi;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_my_favority)
public class MyFavorityModel extends RecyclerModel<MyFavorityActivity,ActivityMyFavorityBinding,Inflate> {
    @Inject MyFavorityModel(){}
    @Inject RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, MyFavorityActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().layoutRecycler.setVm(this);
        api.getMyFavority(new MyFavorityParams("myFavority")).compose(new RestfulTransformer<>())
                .subscribe(list -> {});
    }
}
