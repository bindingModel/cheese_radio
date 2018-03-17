package com.cheese.radio.ui.user.demo;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityDemoBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.media.play.PlayParams;

import javax.inject.Inject;

/**
 * Created by arvin on 2018/3/15.
 */

@ModelView(R.layout.activity_demo)
public class DemoModel extends ViewHttpModel<DemoActivity,ActivityDemoBinding,DemoData>{

    @Inject DemoModel(){}
    @Inject
    RadioApi radioApi;
    @Override
    public void attachView(Bundle savedInstanceState, DemoActivity demoActivity) {
        super.attachView(savedInstanceState, demoActivity);
        setRoHttp((offset1, refresh) -> radioApi.getData().compose(new RestfulTransformer<>()));
//        setRoHttp((offset1, refresh) ->  radioApi.getGroupInfo(new PlayParams("groupInfo","6")).compose(new RestfulTransformer<>()));


    }



    public void onClick(View view){
//        listCompositeDisposable.add(radioApi.getData().compose(new RestfulTransformer<>()).subscribe());
    }

    @Override
    public void accept(DemoData demoData) throws Exception {

    }
}
