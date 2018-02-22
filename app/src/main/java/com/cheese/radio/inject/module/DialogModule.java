package com.cheese.radio.inject.module;


import android.app.Dialog;
import android.content.Context;

import com.cheese.radio.inject.qualifier.context.FragmentContext;
import com.cheese.radio.inject.scope.DialogScope;
import com.cheese.radio.inject.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arvin on 2018/2/1.
 */
@DialogScope
@Module
public class DialogModule {
    private final Dialog dialog;
    public DialogModule(Dialog dialog) {
        this.dialog = dialog;
    }

    @FragmentScope
    @Provides
    @FragmentContext
    Context provideContext() {
        return dialog.getContext();
    }

}
