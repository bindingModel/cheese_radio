package com.cheese.radio.ui.user.phone;


import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPhoneBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.user.login.params.SmsParams;

import javax.inject.Inject;

import okhttp3.internal.Util;

import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;

@ModelView(R.layout.activity_phone)
public class PhoneModel extends ViewHttpModel<PhoneActivity, ActivityPhoneBinding, Object> implements TextWatcher, TextView.OnEditorActionListener {
    @Override
    public void accept(Object o) throws Exception {

    }

    @Inject
    RadioApi api;

    @Inject
    PhoneModel() {
    }

    public ObservableBoolean submitBoolean = new ObservableBoolean(false);
    public BindPhoneParams bindPhoneParams = new BindPhoneParams("bindPhone");
    public SmsParams SMSparams = new SmsParams("sendValidCode", "bindPhone");

    @Override
    public void attachView(Bundle savedInstanceState, PhoneActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().setParams(bindPhoneParams);
        getDataBinding().textPhone.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        String phone = bindPhoneParams.getPhone();
        if (phone.length() == 11 && isValidToast(null, getPhoneError(phone))) {
            SMSparams.setPhone(bindPhoneParams.getPhone());
            addDisposable(api.getSMS(SMSparams).compose(new RestfulTransformer<>())
                    .subscribe(objectInfoEntity -> {
                        BaseUtil.toast("发送短信，请注意查收");
                        submitBoolean.set(true);
                    }, BaseUtil::toast));
        } else submitBoolean.set(false);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    public void onSubmitClick(View view) {
        addDisposable(api.bindPhone(bindPhoneParams).compose(new RestfulTransformer<>()).subscribe(o -> {
        },BaseUtil::toast));
    }
}
