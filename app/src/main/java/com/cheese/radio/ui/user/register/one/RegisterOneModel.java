package com.cheese.radio.ui.user.register.one;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityRegisterOneBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.login.params.MyInfoParams;
import com.cheese.radio.ui.user.register.UserInfoParams;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.registerOne;
import static com.cheese.radio.inject.component.ActivityComponent.Router.registerTwo;

/**
 * Created by 29283 on 2018/3/20.
 */
@ModelView(R.layout.activity_register_one)
public class RegisterOneModel extends ViewModel<RegisterOneActivity, ActivityRegisterOneBinding> {

    @Inject
    RegisterOneModel() {
    }

    @Inject
    RadioApi api;
    private UserInfoParams params = new UserInfoParams("setUserInfo");

    @Override
    public void attachView(Bundle savedInstanceState, RegisterOneActivity registerOneActivity) {
        super.attachView(savedInstanceState, registerOneActivity);
//        api.getMyinfo(new MyInfoParams("myInfo")).compose(new RestfulTransformer<>())
//                .subscribe(o -> {}, BaseUtil::toast);
    }

    public Boolean checkSex = true;
    public ObservableBoolean checkView = new ObservableBoolean(false);

    public void onSelectSexClick(View view) {
        RadioButton textView = (RadioButton) view;
//        textView.getText();
//        getDataBinding().skip.setVisibility(View.VISIBLE);
        switch (textView.getId()) {
            case R.id.boy:
                params.setSex("M");
                break;
            case R.id.girl:
                params.setSex("F");
                break;
        }
        checkView.set(true);
    }


    public void onNextClick(View view) {

        IkeApplication.getUser().setUserEntity(params);
        ARouterUtil.navigation(registerTwo);
        this.finish();
    }

    public void onSkipClick(View view) {
        ARouterUtil.navigation(home);
        this.finish();
    }
}
