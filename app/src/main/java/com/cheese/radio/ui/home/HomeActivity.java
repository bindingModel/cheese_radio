package com.cheese.radio.ui.home;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.view.swipe.SwipeBackLayout;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.ui.broadcast.MusicBroadcast;
import com.cheese.radio.ui.service.AudioServiceUtil;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.ui.Constant.ACTION_BUTTON;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;

/**
 * Created by 29283 on 2018/2/22.
 */
@Route(path = home)
public class HomeActivity extends BaseActivity<HomeModel> {
    private MusicBroadcast receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioServiceUtil.getInstance().bindService(this);
        receiver = new MusicBroadcast();
        registerReceiver(receiver, new IntentFilter(ACTION_BUTTON));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AudioServiceUtil.getInstance().unBindService(this);
        unregisterReceiver(receiver);
        receiver=null;
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
        return SwipeBackLayout.FROM_NO;
    }

//    protected void setFragmentCustomAnimations(){
//        getFragmentManager().beginTransaction().
//    }
}
