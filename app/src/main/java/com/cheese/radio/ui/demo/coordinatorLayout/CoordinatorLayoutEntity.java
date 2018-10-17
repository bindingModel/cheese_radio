package com.cheese.radio.ui.demo.coordinatorLayout;

import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.demo.coordinatorLayout.fragment.DemoFragment;


/**
 * Created by 29283 on 2018/4/20.
 */

public class CoordinatorLayoutEntity extends ViewParse implements Item<BaseFragment> {

    private BaseFragment fragment;

    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if (fragment==null){
            fragment=new DemoFragment();
        }
        return fragment;
    }
}
