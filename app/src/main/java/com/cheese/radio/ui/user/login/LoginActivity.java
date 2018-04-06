package com.cheese.radio.ui.user.login;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.inject.api.RadioApi;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.login;

/**
 * Created by 29283 on 2018/3/5.
 */
@Route(path = login)
public class LoginActivity extends BaseActivity<LoginModel> implements UMAuthListener {
    @Inject RadioApi readApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        请查看你的build.gradle文件，如果 targetSdkVersion小于或等于22，可以忽略这一步，如果大于或等于23，需要做权限的动态申请：
        if(Build.VERSION.SDK_INT>=23){
                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    public void onWechatClick(View view){
        UMShareAPI shareAPI=UMShareAPI.get(this);
        if (UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN)) {
            UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, this);
        }else{
            BaseUtil.toast(this,"您还未安装微信客户端");}
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, vm);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
