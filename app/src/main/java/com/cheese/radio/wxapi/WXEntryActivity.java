package com.cheese.radio.wxapi;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.binding.model.util.BaseUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import java.util.Map;

/**
 * Created by 29283 on 2018/4/3.
 */

public class WXEntryActivity extends WXCallbackActivity implements UMAuthListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
    }

    public void onWechatClick(View view){
        if (UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN)) {
            UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, this);
        }else{
            BaseUtil.toast(this,"您还未安装微信客户端");
         /*   readApi.loginWeChat(
                    "obcej0c2t8b64PPXSb0Lxw3esCzA",
                    "oIQPP1Wy2VIPd6zHmlMlL-r9MPkI",
                    "Arvin",
                    "http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIM0aLd8cjg324wb87TTiah78HA1PoeCG2Tf0yRRGoNLvkj0QkoOgd4F3JyyTMricNrT0X4Al907ib8A/0")
                    .compose(new RestfulTransformer<>())
                    .subscribe(s -> {
                        App.getUser().login(s);
                        BaseUtil.navigation(ActivityComponent.Router.home);
                        BaseUtil.navigation(ActivityComponent.Router.bindPhone);
                        onBackPressed();
                    }, Throwable::printStackTrace);*/
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }


    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }
}
