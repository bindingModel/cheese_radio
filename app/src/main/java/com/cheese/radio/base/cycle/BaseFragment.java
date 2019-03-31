package com.cheese.radio.base.cycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.model.ViewModel;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.inject.component.DaggerFragmentComponent;
import com.cheese.radio.inject.component.FragmentComponent;
import com.cheese.radio.inject.module.FragmentModule;
import com.cheese.radio.ui.CheeseApplication;

import java.lang.reflect.Method;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:00
 * modify developer：  admin
 * modify time：14:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class BaseFragment<VM extends ViewModel> extends DataBindingFragment<FragmentComponent> {
    @Inject
    public VM vm;
    private FragmentComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater,container,savedInstanceState);
        initView(rootView);
        return rootView;
    }

    @SuppressWarnings("unchecked")
    public View inject(Bundle savedInstanceState, ViewGroup parent, boolean attachToParent) {
        View view;
        try {
            Method method = FragmentComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
            view = vm.attachContainer(this, parent, attachToParent,savedInstanceState).getRoot();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("name:%1s need to add @Method inject to FragmentComponent", getClass().getSimpleName()));
        }
        return view;
    }


    public View initView(View rootView) {
        return rootView;
    }


    @Override
    public Activity getDataActivity() {
        return getActivity();
    }


    @Override
    public FragmentComponent getComponent() {
        if (component == null) {
            component = DaggerFragmentComponent.builder()
                    .appComponent(CheeseApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return component;
    }

}
