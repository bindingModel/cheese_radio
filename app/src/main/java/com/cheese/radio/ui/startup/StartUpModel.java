package com.cheese.radio.ui.startup;

import android.os.Bundle;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityStartupBinding;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(R.layout.activity_startup)
public class StartUpModel extends ViewModel<StartUpActivity,ActivityStartupBinding> {
    @Override
    public void attachView(Bundle savedInstanceState, StartUpActivity startUpActivity) {
        super.attachView(savedInstanceState, startUpActivity);

    }
}
