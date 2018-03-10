package com.cheese.radio.ui.login;

import android.os.Bundle;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityLoginBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.activity_login)
public class LoginModel extends ViewModel<LoginActivity,ActivityLoginBinding> {
    @Inject LoginModel(){

    }

    @Override
    public void attachView(Bundle savedInstanceState, LoginActivity loginActivity) {
        super.attachView(savedInstanceState, loginActivity);
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.valueOf(time);
    }
}

