package com.cheese.radio.ui.media.play;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.util.MyBaseUtil;

import static com.cheese.radio.inject.component.ActivityComponent.Router.play;

/**
 * Created by 29283 on 2018/3/17.
 */
@Route(path=play)
public class PlayActivity extends BaseActivity<PlayModel>{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
