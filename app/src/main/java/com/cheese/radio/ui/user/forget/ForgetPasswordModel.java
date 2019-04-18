package com.cheese.radio.ui.user.forget;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityForgetPasswordBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;

import javax.inject.Inject;

@ModelView(R.layout.activity_forget_password)
public class ForgetPasswordModel extends ViewModel<ForgetPasswordActivity, ActivityForgetPasswordBinding> {
    @Inject
    ForgetPasswordModel() {
    }

    @Inject
    RadioApi api;
    private String phone;
    private SignParams params = new SignParams("changePassword");
    private SmsParams smsParams = new SmsParams("sendValidCode", "changePassword");

    @Override
    public void attachView(Bundle savedInstanceState, ForgetPasswordActivity forgetPasswordActivity) {
        super.attachView(savedInstanceState, forgetPasswordActivity);
        getDataBinding().setParams(params);
        phone = forgetPasswordActivity.getIntent().getStringExtra(Constant.phone);
        params.setPhone(phone);
        smsParams.setPhone(phone);
    }

    @Override
    public void onRightClick(View view) {
        if (!params.isConfirmPassword((TextView) view)) return;
        if (!params.isValidSMS((TextView) view)) return;
        addDisposable(api.getToken(params)
                .compose(new ErrorTransform<>())
                .subscribe(
                        signUserEntity -> {
                            if (signUserEntity.code() == 0) BaseUtil.toast("设置密码成功");
                            else BaseUtil.toast(signUserEntity.getMessage());
                        },
                        BaseUtil::toast));
    }

    public void onCodeClick(View view) {
        addDisposable(api.getSMS(smsParams)
                .compose(new ErrorTransform<>())
                .subscribe(objectInfoEntity -> {
                    if (0 == (objectInfoEntity.code()))
                        BaseUtil.sendSMSSuccess((TextView) view);
                    else BaseUtil.sendSMSFailed(view, objectInfoEntity.getMessage());
                }, BaseUtil::toast));
    }
}
