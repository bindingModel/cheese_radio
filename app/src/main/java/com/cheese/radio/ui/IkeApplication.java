package com.cheese.radio.ui;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.binding.model.App;
import com.cheese.radio.BR;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.inject.component.AppComponent;
import com.cheese.radio.inject.component.DaggerAppComponent;
import com.cheese.radio.inject.module.AppModule;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by apple on 2017/6/23.
 */

public class IkeApplication extends MultiDexApplication {
    private static IkeApplication application;
    private static AppComponent appComponent;
//    private User user;
//    public static final PublishSubject<Subject> subject = PublishSubject.create();
//    private DaoUtils daoUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        application = this;
//        daoUtils =  new DaoUtils(this);
        App.getInstance().init(this, BuildConfig.DEBUG, BR.vm);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
//        user = new User(this);
//        MobSDK.init(this);
        PgyCrashManager.register(this);
    }

    public static IkeApplication getApp() {
        return application;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
