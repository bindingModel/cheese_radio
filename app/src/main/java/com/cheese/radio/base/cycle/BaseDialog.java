package com.cheese.radio.base.cycle;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.cycle.CycleContainer;
import com.binding.model.model.ViewModel;
import com.binding.model.util.ReflectUtil;
import com.cheese.radio.inject.component.DaggerDialogComponent;
import com.cheese.radio.inject.component.DialogComponent;
import com.cheese.radio.inject.component.FragmentComponent;
import com.cheese.radio.ui.CheeseApplication;

import java.lang.reflect.Method;

import javax.inject.Inject;

/**
 * Created by arvin on 2018/2/1.
 */

public class BaseDialog<VM extends ViewModel> extends Dialog implements CycleContainer<DialogComponent> {
    private DialogComponent component;
    @Inject VM vm;
    private final int modelIndex;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.modelIndex = 0;
    }
    public BaseDialog(@NonNull Context context, int modelIndex) {
        super(context);
        this.modelIndex = modelIndex;
    }

    public BaseDialog(@NonNull Context context, int themeResId, int modelIndex) {
        super(context, themeResId);
        this.modelIndex = modelIndex;
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener, int modelIndex) {
        super(context, cancelable, cancelListener);
        this.modelIndex = modelIndex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inject(savedInstanceState, null, false));
        vm.setModelIndex(modelIndex);
    }


    @Override
    public DialogComponent getComponent() {
        if (component == null) {
            component = DaggerDialogComponent.builder().appComponent(CheeseApplication.getAppComponent()).build();
        }
        return component;
    }

    @Override
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

    @Override
    public Lifecycle getLifecycle() {
        return ((AppCompatActivity)getDataActivity()).getLifecycle();
    }

    @Override
    public Activity getDataActivity() {
        return (Activity) getContext();
    }
}
