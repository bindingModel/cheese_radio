package com.cheese.radio.inject.module;

import android.content.Context;
import android.view.View;

import com.cheese.radio.inject.qualifier.context.HolderContext;
import com.cheese.radio.inject.scope.HolderScope;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:07
 * modify developer：  admin
 * modify time：10:07
 * modify remark：
 *
 * @version 2.0
 */
@HolderScope
@Module
public class ViewHolderModule {
    private final Context context;

    public ViewHolderModule(View view) {
        this.context =  view.getContext();
    }

    @Provides
    @HolderScope
    @HolderContext
    Context provideActivity(){
        return context;
    }



}
