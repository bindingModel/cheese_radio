package com.cheese.radio.ui.user.login;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.IkeParams;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityLoginBinding;
import com.cheese.radio.inject.api.IkeApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.login.params.SignParams;
import com.cheese.radio.ui.user.login.params.SmsParams;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.activity_login)
public class LoginModel extends ViewModel<LoginActivity,ActivityLoginBinding> {
    @Inject LoginModel(){

    }
    SmsParams SMSparams=new SmsParams();
    SignParams signParams=new SignParams();
    @Inject IkeApi api;
    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
    }

    public void onGetSmsClick(View view){
        if (!signParams.isValidPhone((TextView) view)) return;
        api.getSMS(SMSparams.setPhone(signParams.getPhone()))
                .compose(new ErrorTransform<>())
                .subscribe(objectInfoEntity ->{
                    if(0==(objectInfoEntity.getCode()))
                        BaseUtil.sendSMSSuccess((TextView)view);
                    else BaseUtil.sendSMSFailed(view,objectInfoEntity.getMessage());
                } );
    }
    public void onSignClick(View view){
        if (!signParams.isValidPhone((TextView) view)) return;
        if (!signParams.isValidSMS((TextView) view)) return;
        api.getToken(signParams).compose(new RestfulTransformer<>())
                .subscribe(signUserEntity -> IkeApplication.getUser().setToken(signUserEntity.getToken()));

    }
}

