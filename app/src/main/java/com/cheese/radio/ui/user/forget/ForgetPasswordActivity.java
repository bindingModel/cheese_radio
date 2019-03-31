package com.cheese.radio.ui.user.forget;

import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.inject.component.ActivityComponent;

@Route(path = ActivityComponent.Router.forget)
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordModel> {

    @Override
    public void initToolBar(Toolbar view) {
        super.initToolBar(view);
        RadioButton radioButton = view.findViewById(R.id.toolbar_right);
        radioButton.setText(R.string.commit);
    }
}
