package com.cheese.radio.ui.home.circle.join;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCircleJoinBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;

import javax.inject.Inject;

import static com.binding.model.util.BaseUtil.getPhoneError;
import static com.binding.model.util.BaseUtil.isValidToast;

/**
 * @author 29283
 * @create 2018/9/25.
 */
@ModelView(R.layout.activity_circle_join)
public class JoinCircleDetailModel extends ViewModel<JoinCircleDetailActivity, ActivityCircleJoinBinding> {
    @Inject
    JoinCircleDetailModel() {
    }

    @Inject
    RadioApi api;
    private JoinCircleDetailParams params = new JoinCircleDetailParams("actRegist");

    @Override
    public void attachView(Bundle savedInstanceState, JoinCircleDetailActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().setParams(params);
        params.setActivityid(activity.getIntent().getIntExtra(Constant.id, 0));
    }

    public void onSubmitClick(View view) {
        if (isValidToast(view, getPhoneError(params.getPhone())) && !TextUtils.isEmpty(params.getContacts()))
            addDisposable(api.circleDatetailEnroll(params).compose(new ErrorTransform<>())
                    .subscribe((s -> {
                        if ("0".equals(s.getCode())){
                            BaseUtil.toast("报名成功！");
                            finish();
                        }
                    }), BaseUtil::toast));
        else BaseUtil.toast("名字或手机号不正确！");
    }
}
