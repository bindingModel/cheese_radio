package com.cheese.radio.ui.home.mine;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.FragmentHomeMineBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.ui.user.my.push.NewMessageCountParams;
import com.cheese.radio.ui.user.profile.ProfileParams;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(value = R.layout.fragment_home_mine, event = R.id.HomeMineModel, model = true)
public class HomeMineModel extends ViewModel<HomeMineFragment, FragmentHomeMineBinding> {
    @Inject
    HomeMineModel() {
    }

    public ObservableBoolean redTipBoolean = new ObservableBoolean(false);
    public ObservableField<String> redTipCount = new ObservableField<>("0");
    public ObservableField<Drawable> head = new ObservableField<>();
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, HomeMineFragment homeMineFragment) {
        super.attachView(savedInstanceState, homeMineFragment);
        updataUI();
        upDataMsg();

    }

    public void upDataMsg(){
        if (CheeseApplication.isLogin(true)) {
            addDisposable(api.getNewMessageCount(new NewMessageCountParams("newMessageCount")).compose(new RestfulTransformer<>())
                    .subscribe(newMessageCountData -> {
                        redTipCount.set(String.valueOf(newMessageCountData.getCount()));
                        if (newMessageCountData.getCount() != null && newMessageCountData.getCount() != 0)
                            redTipBoolean.set(true);
                        else redTipBoolean.set(false);
                    }, BaseUtil::toast));
        }else finish();
    }
    public void onLogoutClick(View view) {
        CheeseApplication.getUser().logout();
        this.finish();
    }

    public void logout() {
        getDataBinding().setEntity(CheeseApplication.getUser().getUserEntity());
    }

    public void onSetProfileClick(View view) {
        if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(ActivityComponent.Router.profile);
        else finish();
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
        ProfileParams params = event instanceof ProfileParams ? (ProfileParams) event : null;
        if (params != null) CheeseApplication.getUser().setUserEntity(params);
        getDataBinding().setEntity(CheeseApplication.getUser().getUserEntity());
        return 1;
    }

    public void onCourseClick(View view) {
        if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(ActivityComponent.Router.course);
        else finish();
    }

    public void onWorkClick(View view) {
        if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(ActivityComponent.Router.work);
        else finish();
    }

    public void onFavotity(View view) {
        if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(ActivityComponent.Router.favority);
        else finish();
    }

    public void onMessageClick(View view) {
        if (CheeseApplication.isLogin(true)) ARouterUtil.navigation(ActivityComponent.Router.message);
        else finish();

    }

    public Drawable getSex() {
        return CheeseApplication.getUser().getUserEntity().getSex().equals("M") ?
                App.getDrawable(R.mipmap.boy) : App.getDrawable(R.mipmap.girl);
    }

    private void updataUI() {
        getDataBinding().setEntity(CheeseApplication.getUser().getUserEntity());
        head.set(getSex());
      if( !TextUtils.isEmpty(CheeseApplication.getUser().getUserEntity().getPortrait())) {
            getDataBinding().defHead.setVisibility(View.GONE);
      }
    }

    public void onCenterClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.center);
    }
    public void onSafeClick(View view){
        if(CheeseApplication.isLogin(true))
        ARouterUtil.navigation(ActivityComponent.Router.safe);
        else finish();
    }
}
