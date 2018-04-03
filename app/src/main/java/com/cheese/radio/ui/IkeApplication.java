package com.cheese.radio.ui;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.binding.model.App;
import com.cheese.radio.BR;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.AppComponent;
import com.cheese.radio.inject.component.DaggerAppComponent;
import com.cheese.radio.inject.module.AppModule;
import com.cheese.radio.ui.user.User;
import com.pgyersdk.crash.PgyCrashManager;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.login;

/**
 * Created by apple on 2017/6/23.
 */

public class IkeApplication extends MultiDexApplication {
    private static IkeApplication application;
    private static AppComponent appComponent;
    private User user;
    @Inject
    RadioApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        application = this;
        App.getInstance().init(this, BuildConfig.DEBUG, BR.vm);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
        user = new User(this);
        PgyCrashManager.register(this);
    }

    public static IkeApplication getApp() {
        return application;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static boolean isLogin() {
        if (!TextUtils.isEmpty(getUser().getToken()))
            return true;
        else {
            ARouterUtil.navigation(login);
            return false;
        }
    }

    public static User getUser() {
        return application.user;
    }

}
