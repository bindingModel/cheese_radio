package com.cheese.radio.ui.startup;

import android.os.Bundle;
import android.os.Handler;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivityStartupBinding;
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.util.NetUtil;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.welcome;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(R.layout.activity_startup)
public class StartUpModel extends ViewModel<StartUpActivity, ActivityStartupBinding> {
    @Inject
    StartUpModel() {
    }
    private long time422 =1558454400000L;


    @Override
    public void attachView(Bundle savedInstanceState, StartUpActivity startUpActivity) {
        super.attachView(savedInstanceState, startUpActivity);
        Integer time = 100;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(2019,6,22));
        NetUtil.getMacAddress();
            if (System.currentTimeMillis() < calendar.getTimeInMillis()) {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (CheeseApplication.getUser().checkIsFirstUse() == 0)
                    ARouterUtil.navigation(welcome);
                else if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(home);
                finish();
            }, time);
        }
    }
}
