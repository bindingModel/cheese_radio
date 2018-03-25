package com.cheese.radio.ui.home.mine;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.FragmentHomeMineBinding;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.page.HomePageModel;
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

    @Override
    public void attachView(Bundle savedInstanceState, HomeMineFragment homeMineFragment) {
        super.attachView(savedInstanceState, homeMineFragment);
        getDataBinding().setEntity(IkeApplication.getUser().getUserEntity());
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
}
