package com.cheese.radio.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.App;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.service.AudioServiceUtil;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;

/**
 * Created by 29283 on 2018/2/22.
 */
@Route(path=home)
public class HomeActivity extends BaseActivity <HomeModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioServiceUtil.getInstance().bindService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AudioServiceUtil.getInstance().unBindService(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
