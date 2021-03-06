package com.cheese.radio.inject.component;

import android.content.Context;
import android.content.res.Resources;

import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.module.AppModule;
import com.cheese.radio.inject.module.DataModule;
import com.cheese.radio.inject.module.NetWorkModule;
import com.cheese.radio.inject.qualifier.context.AppContext;
import com.cheese.radio.inject.scope.ApplicationScope;
import com.cheese.radio.ui.CheeseApplication;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:08
 * modify developer：  admin
 * modify time：10:08
 * modify remark：
 *
 * @version 2.0
 */

@ApplicationScope
@Component(modules={AppModule.class, NetWorkModule.class,DataModule.class})
public interface AppComponent {
    void inject(CheeseApplication application);

    @AppContext
    Context context();
    Resources resources();
    RadioApi getIkeApi();
}
