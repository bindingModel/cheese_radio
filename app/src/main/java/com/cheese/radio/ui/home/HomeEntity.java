package com.cheese.radio.ui.home;

import android.app.Application;
import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.circle.CircleFragment;
import com.cheese.radio.ui.home.clock.ClockFragment;
import com.cheese.radio.ui.home.mine.HomeMineFragment;
import com.cheese.radio.ui.home.page.HomePageFragment;
import com.cheese.radio.ui.user.calendar.CalendarFragment;

/**
 * Created by 29283 on 2018/2/22.
 */

public class HomeEntity extends ViewParse implements Item<BaseFragment> {
    private BaseFragment fragment;

    //判断是否显示日历


    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if (fragment == null)
            switch (position) {
                case 0:
                    fragment = new HomePageFragment();
                    break;
                case 1: {
                    if (IkeApplication.getUser().getCanBookCheck())
                        fragment = new CalendarFragment();
                    else {
                        fragment = new ClockFragment();
                    }
                    break;

                }
                case 2:
                    fragment = new CircleFragment();
                    break;
                case 3:
                    fragment = new HomeMineFragment();
                    break;
            }
        return fragment;
    }
}