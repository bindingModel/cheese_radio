package com.cheese.radio.ui.user.login;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.binding.model.App;
import com.binding.model.Config;
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
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.cheese.radio.ui.user.register.UserInfoParams;
import com.cheese.radio.util.MyBaseUtil;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.demo;
import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.registerOne;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.activity_login)
public class LoginModel extends ViewModel<LoginActivity, ActivityLoginBinding> implements UMAuthListener,TextView.OnEditorActionListener  {
    @Inject
    LoginModel() {

    }

    private PlatformParams platformParams = new PlatformParams("openPlatformConfig");
    private SmsParams SMSparams = new SmsParams("sendValidCode", "login");
    private SignParams signParams = new SignParams("login");
    public ObservableBoolean input = new ObservableBoolean(true);
    @Inject
    RadioApi api;
    TextView textView;
    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
        getDataBinding().setParams(signParams);
        getDataBinding().inputPhone.setOnEditorActionListener(this);
//        getDataBinding().inputPassword.setOnEditorActionListener(this);
        getDataBinding().inputCode.setOnEditorActionListener(this);
        textView=getDataBinding().msgCode;
    }

    public void onGetSmsClick(View view) {
        if (!signParams.isValidPhone((TextView) view)) return;
        addDisposable(api.getSMS(SMSparams.setPhone(signParams.getPhone()))
                .compose(new ErrorTransform<>())
                .subscribe(objectInfoEntity -> {
                    if (0 == (objectInfoEntity.code()))
                        BaseUtil.sendSMSSuccess((TextView) view);
                    else BaseUtil.sendSMSFailed(view, objectInfoEntity.getMessage());
                },BaseUtil::toast));
    }

    public void onInputClick(View view){
        input.set(!input.get());
    }

    public void onSignClick(View view) {
        if (!signParams.isValidPhone((TextView) view)) return;
        if(input.get()){
            if (!signParams.isValidSMS((TextView) view)) return;
            signParams.setLoginType("phone");
        }else {
            if(signParams.isValidPassword((TextView)view))return;
            signParams.setLoginType("password");
        }
        login(signParams);
    }

    public void onForgetClick(View view){
        if (!signParams.isValidPhone(getDataBinding().inputPhone))return;
        Bundle bundle = new Bundle();
        bundle.putString(Config.title, App.getString(R.string.forget_password));
        bundle.putString(Constant.phone,getDataBinding().inputPhone.getText().toString());
        ARouterUtil.navigation(ActivityComponent.Router.forget,bundle);
    }

    public void onGoHomeClick(View view) {
        ARouterUtil.navigation(home);
        finish();
    }

    public void onPlatformClick(View view) {

        switch (view.getId()) {
            case R.id.qq:
                platformParams.setPlatform("qq");
                break;
            case R.id.wei_bo:
                platformParams.setPlatform("weibo");
                break;
            case R.id.wei_xin:
                platformParams.setPlatform("weixin");
                break;
        }
        ARouterUtil.navigation(demo);
        BaseUtil.toast("还在努力建设中");

    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    /**
     * 第三方接口回调的参数
     *
     * @param share_media
     * @param i
     * @param map         uid 用户唯一标识
     *                    name 用户昵称
     *                    gender 用户性别
     *                    iconurl 用户头像
     */
    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        //打印。。。数据内容
        //第三方登录中uid 才是真正的openid
        Gson gson=new Gson();
        SignParams params = new SignParams("login");
        params.setLoginType("weixin");
        params.setOpenId(map.get("uid"));
        params.setOpenId2(map.get("openid"));
        Map<String,String> infoMap=new HashMap<>();
        infoMap.put("openid",map.get("openid"));
        infoMap.put("unionid",map.get("unionid"));
        infoMap.put("scope",map.get("scope"));
        infoMap.put("expires_in",map.get(" expires_in"));
        infoMap.put("refreshToken",map.get("refresh_token"));
        infoMap.put("access_token",map.get("access_token"));
        params.setOtherinfo(gson.toJson(infoMap));
        login(params);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }

    private void login(SignParams params) {
        addDisposable(api.getToken(params).compose(new RestfulTransformer<>())
                .subscribe(signUserEntity -> {
                    CheeseApplication.getUser().setToken(signUserEntity.getToken());
                    if (signUserEntity.getNewX() == 0) {
                        ARouterUtil.navigation(home);
                        addDisposable(api.getUserInfo(new UserInfoParams("myInfo")).compose(new RestfulTransformer<>()).subscribe(userEntity -> {
                            CheeseApplication.getUser().setUserEntity(userEntity);
                        }, BaseUtil::toast));
                    } else ARouterUtil.navigation(registerOne);
                    finish();
                }, BaseUtil::toast));

    }

    /**
     * Called when an action is being performed.
     *
     * @param v        The view that was clicked.
     * @param actionId Identifier of the action.  This will be either the
     *                 identifier you supplied, or {@link EditorInfo#IME_NULL
     *                 EditorInfo.IME_NULL} if being called due to the enter key
     *                 being pressed.
     * @param event    If triggered by an enter key, this is the event;
     *                 otherwise, this is null.
     * @return Return true if you have consumed the action, else false.
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            if (!signParams.isValidPhone((TextView) v)) return true;
            if (!signParams.isValidSMS((TextView) v)) return true;
            signParams.setLoginType("phone");
            login(signParams);
            MyBaseUtil.HideKeyboard(v);
            return false;
        }
        if(actionId == EditorInfo.IME_ACTION_NEXT )
        {
            onGetSmsClick(textView);
        }
        return false;
    }
}

