package com.cheese.radio.ui.home;

import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.home.mine.HomeMineFragment;

/**
 * Created by 29283 on 2018/2/22.
 */

public class HomeEntity extends ViewParse implements Item<BaseFragment> {
    private BaseFragment fragment;

    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if (fragment == null)
            switch (position) {

                case 3:fragment = new HomeMineFragment();break;
            }
        return fragment;
    }
}