package com.cheese.radio.ui.demo.coordinatorLayout;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;


import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;

import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityDemoBinding;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/4/20.
 */
@ModelView(R.layout.activity_demo)
public class CoordinatorLayoutModel extends PagerModel<CoordinatorLayoutActivity,ActivityDemoBinding,CoordinatorLayoutEntity> {
    @Inject
    CoordinatorLayoutModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    private final List<CoordinatorLayoutEntity> fragmentList =new ArrayList<>();
    @Override
    public void attachView(Bundle savedInstanceState, CoordinatorLayoutActivity coordinatorLayoutActivity) {
        super.attachView(savedInstanceState, coordinatorLayoutActivity);
    }

    public void getFragment(){
        if(fragmentList.isEmpty()){
            for (int i = 0; i <2 ; i++) {
                fragmentList.add(new CoordinatorLayoutEntity());
            }
        }
    }
}

