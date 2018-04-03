package com.cheese.radio.ui.user.login;

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
}
