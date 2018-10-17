package com.cheese.radio.ui.startup;

import android.os.Bundle;
import android.os.Handler;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivityStartupBinding;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.util.NetUtil;

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

    @Override
    public void attachView(Bundle savedInstanceState, StartUpActivity startUpActivity) {
        super.attachView(savedInstanceState, startUpActivity);
        Integer time = 100;
        NetUtil.getMacAddress();
//        ARouterUtil.navigation(ActivityComponent.Router.registerOne);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (IkeApplication.getUser().checkIsFirstUse() == 0) ARouterUtil.navigation(welcome);
            else if (IkeApplication.isLogin(true)) ARouterUtil.navigation(home);

            finish();
        }, time);
    }
}
