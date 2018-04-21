package com.cheese.radio.ui.user.register.two;

import android.content.res.ColorStateList;
import android.databinding.ObservableBoolean;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityRegisterTwoBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.register.UserInfoParams;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;

/**
 * Created by 29283 on 2018/3/20.
 */
@ModelView(R.layout.activity_register_two)
public class RegisterTwoModel extends ViewModel<RegisterTwoActivity, ActivityRegisterTwoBinding> {
    @Inject
    RegisterTwoModel() {
    }

    @Inject
    RadioApi api;
    private UserInfoParams params = new UserInfoParams("setUserInfo");
    public ObservableBoolean checkView = new ObservableBoolean(false);

    @Override
    public void attachView(Bundle savedInstanceState, RegisterTwoActivity activity) {
        super.attachView(savedInstanceState, activity);
    }

    public void onNextClick(View view) {
        params.setSex(IkeApplication.getUser().getUserEntity().getSex());
        addDisposable(api.setUserInfo(params).compose(new ErrorTransform<>()).subscribe(s -> {
            if (s.getCode().equals("0")) {
                ARouterUtil.navigation(home);
                IkeApplication.getUser().setUserEntity(params);
                this.finish();
            }
        }, BaseUtil::toast));
    }

    public void onSeleckAgeClick(View view) {
        checkView.set(true);
        switch (view.getId()) {
            case R.id.select_for:
                params.setAge("4~5");
                getDataBinding().selectFor.setTextSize(23);
                getDataBinding().selectFor.setTextColor(Color.BLACK);
                getDataBinding().selectSix.setTextSize(16);
                getDataBinding().selectSix.setTextColor(Color.GRAY);
                break;
            case R.id.select_six:
                params.setAge("6~8");
                getDataBinding().selectFor.setTextSize(16);
                getDataBinding().selectFor.setTextColor(Color.GRAY);
                getDataBinding().selectSix.setTextSize(23);
                getDataBinding().selectSix.setTextColor(Color.BLACK);
                break;
            default:

        }

    }

    public void onSkipClick(View view) {
        ARouterUtil.navigation(home);
        this.finish();
    }

}
