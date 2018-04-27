package com.cheese.radio.ui.user.login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.cheese.radio.ui.user.User;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;
import com.cheese.radio.ui.user.profile.ProfileParams;
import com.cheese.radio.ui.user.register.UserInfoParams;
import com.cheese.radio.util.MyBaseUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

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
    @Inject
    RadioApi api;
    TextView textView;
    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
        getDataBinding().setParams(signParams);
        getDataBinding().inputPhone.setOnEditorActionListener(this);
        getDataBinding().inputPassword.setOnEditorActionListener(this);
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
                }));
    }

    public void onSignClick(View view) {
        if (!signParams.isValidPhone((TextView) view)) return;
        if (!signParams.isValidSMS((TextView) view)) return;
        signParams.setLoginType("phone");
        login(signParams);
    }

    public void onGoHomeClick(View view) {
        ARouterUtil.navigation(home);
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
        BaseUtil.toast("暂未施工");

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
        StringBuilder builder = new StringBuilder().append("{");
        for (String key : map.keySet()) {
            Timber.i("key:%1s,value:%2s", key, map.get(key));
            builder.append(key).append(":").append(map.get(key)).append(",");
        }
        builder.setLength(builder.length() - 1);
        builder.append("}");
        SignParams params = new SignParams("login");
        params.setLoginType("weixin");
        params.setOpenId(map.get("uid"));
//        params.setOtherInfo(builder.toString());
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
                    IkeApplication.getUser().setToken(signUserEntity.getToken());
                    if (signUserEntity.getNewX() == 0) {
                        ARouterUtil.navigation(home);
                        addDisposable(api.getUserInfo(new UserInfoParams("myInfo")).compose(new RestfulTransformer<>()).subscribe(userEntity -> {
                            IkeApplication.getUser().setUserEntity(userEntity);
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

