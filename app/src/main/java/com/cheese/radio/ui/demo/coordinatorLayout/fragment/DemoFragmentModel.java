package com.cheese.radio.ui.demo.coordinatorLayout.fragment;

import android.os.Bundle;

import com.binding.model.adapter.IEventAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentDemoBinding;
import com.cheese.radio.ui.user.my.favority.MyFavorityTitle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/4/20.
 */
@ModelView(R.layout.fragment_demo)
public class DemoFragmentModel extends RecyclerModel<DemoFragment,FragmentDemoBinding,Inflate> {
    @Inject DemoFragmentModel(){}
        private List<Inflate> list=new ArrayList<>();
    @Override
    public void attachView(Bundle savedInstanceState, DemoFragment demoFragment) {
        super.attachView(savedInstanceState, demoFragment);
        getDataBinding().layoutRecycler.setVm(this);
        for (int i = 0; i <15 ; i++) {
            list.add(new MyFavorityTitle("i="+i));
        }
        try {
            getAdapter().addListAdapter(IEventAdapter.NO_POSITION,list);
        } catch (Exception e) {
            BaseUtil.toast(e.toString());
        }
    }
}
