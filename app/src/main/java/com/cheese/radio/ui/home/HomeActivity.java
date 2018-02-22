package com.cheese.radio.ui.home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;

/**
 * Created by 29283 on 2018/2/22.
 */
@Route(path=home)
public class HomeActivity extends BaseActivity <HomeModel> {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
