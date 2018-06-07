package com.cheese.radio.base.cycle;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.binding.model.cycle.ActivityContainer;
import com.binding.model.cycle.DataBindingActivity;
import com.binding.model.model.ViewModel;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.inject.component.DaggerActivityComponent;
import com.cheese.radio.inject.module.ActivityModule;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.util.MyBaseUtil;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Method;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：12:00
 * modify developer：  admin
 * modify time：12:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class BaseActivity<VM extends ViewModel> extends DataBindingActivity<ActivityComponent> implements ActivityContainer<ActivityComponent> {
    @Inject
    public VM vm;
    private ActivityComponent mActivityComponent;

    @SuppressWarnings("unchecked")
    public View inject(Bundle savedInstanceState, ViewGroup parent, boolean attachToParent) {
        View rootView;
        try {
            Method method = ActivityComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
            rootView = vm.attachContainer(this,parent,attachToParent,savedInstanceState).getRoot();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("name:%1s need to add @Method inject to ActivityComponent", getClass().getSimpleName()));
        }
        return rootView;
    }

//    @Override
//    protected View getContainer(View rootView, int v) {
//        return super.getContainer(rootView, v);
//    }

    @Override
    public void initToolBar(Toolbar view) {

    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) winParams.flags |= bits;
        else winParams.flags &= ~bits;
        win.setAttributes(winParams);
    }

    @Override
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(IkeApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public AppCompatActivity getDataActivity() {
        return this;
    }


    public void onRightClick(View view) {
        vm.onRightClick(view);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vm.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackClick(View view) {
        super.onBackClick(view);

    }
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyBaseUtil.checkActivity(this);
    }
}
