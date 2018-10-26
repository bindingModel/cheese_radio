package com.cheese.radio.ui.user.enroll;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.ui.IkeApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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
        String wechat_AppID = getResources().getString(R.string.wechat_AppID);
        String wechat_AppSecret = getResources().getString(R.string.wechat_AppSecret);
        iwxapi = WXAPIFactory.createWXAPI(this,wechat_AppID, false);
        iwxapi.registerApp(wechat_AppID);
        IkeApplication.registerWX(wechat_AppID,wechat_AppSecret);
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
