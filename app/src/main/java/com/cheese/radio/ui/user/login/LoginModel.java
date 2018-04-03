package com.cheese.radio.ui.user.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityLoginBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.registerOne;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.activity_login)
public class LoginModel extends ViewModel<LoginActivity, ActivityLoginBinding> {
    @Inject
    LoginModel() {

    }

    private PlatformParams platformParams = new PlatformParams("openPlatformConfig");
    private SmsParams SMSparams = new SmsParams("sendValidCode", "login");
    private SignParams signParams = new SignParams("login");
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
        getDataBinding().setParams(signParams);
    }

    public void onGetSmsClick(View view) {
        if (!signParams.isValidPhone((TextView) view)) return;
        api.getSMS(SMSparams.setPhone(signParams.getPhone()))
                .compose(new ErrorTransform<>())
                .subscribe(objectInfoEntity -> {
                    if (0 == (objectInfoEntity.getCode()))
                        BaseUtil.sendSMSSuccess((TextView) view);
                    else BaseUtil.sendSMSFailed(view, objectInfoEntity.getMessage());
                });
    }

    public void onSignClick(View view) {
        if (!signParams.isValidPhone((TextView) view)) return;
        if (!signParams.isValidSMS((TextView) view)) return;
        signParams.setLoginType("phone");
        api.getToken(signParams).compose(new RestfulTransformer<>())
                .subscribe(signUserEntity -> {
                    IkeApplication.getUser().setToken(signUserEntity.getToken());
                    if(signUserEntity.getNewX()==0)ARouterUtil.navigation(home);
                    else ARouterUtil.navigation(registerOne);
                    finish();
                }, BaseUtil::toast);

    }

    public void onGoHomeClick(View view) {
        ARouterUtil.navigation(home);
    }

    public void onPlatformClick(View view) {

        switch (view.getId()) {
            case R.id.qq:
                platformParams.setOpenPlatformConfig("qq");break;
            case R.id.wei_bo:
                platformParams.setOpenPlatformConfig("weibo");break;
            case R.id.wei_xin:
                platformParams.setOpenPlatformConfig("weixin");break;
        }
        BaseUtil.toast("暂未施工");

    }

}

