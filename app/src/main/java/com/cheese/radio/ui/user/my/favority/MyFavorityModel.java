package com.cheese.radio.ui.user.my.favority;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMyFavorityBinding;
import com.cheese.radio.inject.api.RadioApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_my_favority)
public class MyFavorityModel extends RecyclerModel<MyFavorityActivity, ActivityMyFavorityBinding, Inflate> {
    @Inject
    MyFavorityModel() {
    }

    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, MyFavorityActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().layoutRecycler.setVm(this);
        setRcHttp((offset1, refresh) -> api.getMyFavority(new MyFavorityParams("myFavority")).compose(new RestfulTransformer<>()
        ).map(myFavorityData -> {
            List<Inflate> list = new ArrayList<>();
           if(myFavorityData.getSingle()!=null) {
//               list.add(new MyFavorityTitle("故事",myFavorityData.getSingle().getTotal()));
            list.addAll(myFavorityData.getSingle().getList());}
            if(myFavorityData.getGroup()!=null) {
//                list.add(new MyFavorityTitle("专辑"));
            list.addAll(myFavorityData.getGroup().getList());}
            return list;
        }));
    }
}
