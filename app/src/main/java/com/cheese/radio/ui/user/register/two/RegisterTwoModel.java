package com.cheese.radio.ui.user.register.two;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityRegisterTwoBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/20.
 */
@ModelView(R.layout.activity_register_two)
public class RegisterTwoModel extends ViewModel<RegisterTwoActivity,ActivityRegisterTwoBinding>{
    @Inject RegisterTwoModel(){}

    @Override
    public void attachView(Bundle savedInstanceState, RegisterTwoActivity activity) {
        super.attachView(savedInstanceState, activity);
    }
}
