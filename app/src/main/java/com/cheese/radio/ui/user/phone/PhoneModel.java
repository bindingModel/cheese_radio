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
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityPhoneBinding;

import javax.inject.Inject;

@ModelView(R.layout.activity_phone)
public class PhoneModel extends ViewHttpModel  <PhoneActivity,ActivityPhoneBinding,Object> implements TextWatcher, TextView.OnEditorActionListener{
    @Override
    public void accept(Object o) throws Exception {

    }
    @Inject PhoneModel(){}
    public ObservableBoolean submitBoolean=new ObservableBoolean(false);

    @Override
    public void attachView(Bundle savedInstanceState, PhoneActivity activity) {
        super.attachView(savedInstanceState, activity);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if(TextUtils.isEmpty(s))submitBoolean.set(false);
        else submitBoolean.set(true);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
    public void onSubmitClick(View view){

    }
}
