package com.cheese.radio.ui.home;

import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.home.circle.CircleFragment;
import com.cheese.radio.ui.home.clock.ClockFragment;
import com.cheese.radio.ui.home.mine.HomeMineFragment;
import com.cheese.radio.ui.home.page.HomePageFragment;

/**
 * Created by 29283 on 2018/2/22.
 */

public class HomeEntity extends ViewParse implements Item<BaseFragment> {
    private BaseFragment fragment;

    //判断是否显示日历


    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
     /*   if (fragment instanceof CalendarFragment && !CheeseApplication.getUser().getCanBookCheck()) {
            fragment = new ClockFragment();
        } else if (fragment instanceof ClockFragment && CheeseApplication.getUser().getCanBookCheck()) {
            fragment = new CalendarFragment();
        }*/
        if (fragment == null)
            switch (position) {
                case 0:
                    fragment = new HomePageFragment();
                    break;
                case 1: {
//                    fragment = CheeseApplication.getUser().getCanBookCheck() ? new CalendarFragment() : new ClockFragment();
//                    fragment =new CalendarFragment();
                    fragment=new ClockFragment();
                    break;
                }
                case 2:
                    fragment = new CircleFragment();
                    break;
                case 3:
                    fragment = new HomeMineFragment();
                    break;
                default:
            }
        return fragment;
    }
}