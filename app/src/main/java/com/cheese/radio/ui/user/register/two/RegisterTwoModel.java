package com.cheese.radio.ui.user.register.two;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
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
        Disposable subscribe = api.setUserInfo(params).compose(new RestfulTransformer<>()).subscribe(s -> {
            ARouterUtil.navigation(home);
            this.finish();
        }, BaseUtil::toast);
    }

    public void onSeleckAgeClick(View view) {
        checkView.set(true);
        switch (view.getId()) {
            case R.id.select_for:
                params.setAge("4~5");
                getDataBinding().selectFor.setTextSize(21);
                getDataBinding().selectSix.setTextSize(17);
                break;
            case R.id.select_six:
                params.setAge("6~7");
                getDataBinding().selectFor.setTextSize(17);
                getDataBinding().selectSix.setTextSize(21);
                break;
            default:
                IkeApplication.getUser().setUserEntity(params);
        }

    }

    public void onSkipClick(View view) {
        ARouterUtil.navigation(home);
        this.finish();
    }

}
