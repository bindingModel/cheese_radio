package com.cheese.radio.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.App;
import com.binding.model.util.BaseUtil;
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
//        super.onBackPressed();
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        onPause();
        onStop();
//        System.exit(0);
    }

    @Override
    protected int isSwipe() {
        return 0;
    }

//    protected void setFragmentCustomAnimations(){
//        getFragmentManager().beginTransaction().
//    }
}
