package com.cheese.radio.ui.user.enroll;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.ui.CheeseApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;


/**
 * Created by 29283 on 2018/3/10.
 */
@Route(path = enroll)
public class EnrollActivity extends BaseActivity<EnrollModel> {
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String wechat_AppID = BuildConfig.wechat_AppID;
        String wechat_AppSecret = BuildConfig.wechat_AppSecret;
        iwxapi = WXAPIFactory.createWXAPI(this,wechat_AppID, false);
        iwxapi.registerApp(wechat_AppID);
        CheeseApplication.registerWX(wechat_AppID,wechat_AppSecret);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (iwxapi != null) iwxapi.unregisterApp();
        iwxapi = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public IWXAPI getIwxapi() {
        return iwxapi;
    }
}
