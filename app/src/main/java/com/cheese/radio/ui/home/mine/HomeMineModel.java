package com.cheese.radio.ui.home.mine;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.FragmentHomeMineBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.page.HomePageModel;
import com.cheese.radio.ui.user.my.push.NewMessageCountParams;
import com.cheese.radio.ui.user.profile.ProfileParams;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(value = R.layout.fragment_home_mine, event = R.id.HomeMineModel)
public class HomeMineModel extends ViewModel<HomeMineFragment, FragmentHomeMineBinding> {
    @Inject
    HomeMineModel() {
    }

    public ObservableBoolean redTipBoolean=new ObservableBoolean(false);
    public ObservableField<String> redTipCount=new ObservableField<>("0");
    @Inject RadioApi api;
    @Override
    public void attachView(Bundle savedInstanceState, HomeMineFragment homeMineFragment) {
        super.attachView(savedInstanceState, homeMineFragment);
        getDataBinding().setEntity(IkeApplication.getUser().getUserEntity());
        api.getNewMessageCount(new NewMessageCountParams("newMessageCount")).compose(new RestfulTransformer<>())
                .subscribe(newMessageCountData -> {
                    redTipCount.set(String.valueOf(newMessageCountData.getCount()));
                    if(newMessageCountData.getCount()!=null & newMessageCountData.getCount()!=0)
                        redTipBoolean.set(true);
                    else redTipBoolean.set(false);
                });
    }

    public void onLogoutClick(View view) {
        IkeApplication.getUser().logout();
    }

    public void onSetProfileClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.profile);
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
        ProfileParams params = event instanceof ProfileParams ? (ProfileParams) event : null;
        if (params != null) IkeApplication.getUser().setUserEntity(params);
        getDataBinding().setEntity(IkeApplication.getUser().getUserEntity());
        return 1;
    }

    public void onCourseClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.course);
    }
    public void onWorkClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.work);
    }
    public void onFavotity(View view){
        ARouterUtil.navigation(ActivityComponent.Router.favority);
    }
    public void onMessageClick(View view){ARouterUtil.navigation(ActivityComponent.Router.message);}
}
