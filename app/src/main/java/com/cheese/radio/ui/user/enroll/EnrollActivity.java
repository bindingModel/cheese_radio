package com.cheese.radio.ui.user.enroll;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;


/**
 * Created by 29283 on 2018/3/10.
 */
@Route(path=enroll)
public class EnrollActivity extends BaseActivity<EnrollModel> {
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iwxapi = WXAPIFactory.createWXAPI(this, this.getString(R.string.wechat_AppID), false);
        iwxapi.registerApp(this.getString(R.string.wechat_AppID));
        String wechat_AppID = getResources().getString(R.string.wechat_AppID);
        String wechat_AppSecret = getResources().getString(R.string.wechat_AppSecret);
        PlatformConfig.setWeixin(wechat_AppID, wechat_AppSecret);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(iwxapi!=null) iwxapi.unregisterApp();
        iwxapi=null;
    }

    public IWXAPI getIwxapi(){
        return iwxapi;
    }
}
