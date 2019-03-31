package com.cheese.radio.ui.user.calendar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.view.swipe.SwipeBackLayout;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.inject.component.ActivityComponent;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.ui.user.calendar
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/11 6:46 PM
 * @change
 * @chang time
 * @class describe
 */
@Route(path = ActivityComponent.Router.calendar)
public class CalendarActivity extends BaseActivity<Calendar3Model> {
    @Override
    protected int isSwipe() {
        return SwipeBackLayout.FROM_NO;
    }
}
