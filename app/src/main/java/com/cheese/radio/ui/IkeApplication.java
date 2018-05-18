package com.cheese.radio.ui;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.binding.model.App;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.BR;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.AppComponent;
import com.cheese.radio.inject.component.DaggerAppComponent;
import com.cheese.radio.inject.module.AppModule;
import com.cheese.radio.ui.user.User;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.cheese.radio.util.MyBaseUtil;
import com.pgyersdk.crash.PgyCrashManager;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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
        String wechat_AppID = getResources().getString(R.string.wechat_AppID);
        String wechat_AppSecret = getResources().getString(R.string.wechat_AppSecret);
        PlatformConfig.setWeixin(wechat_AppID, wechat_AppSecret);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, ""
                , "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //td
        String td_AppId=getResources().getString(R.string.td_app_id);
        String td_AppChannel=getResources().getString(R.string.td_app_channel);
        TCAgent.LOG_ON=true;
        TCAgent.init(this, td_AppId, td_AppChannel);
        TCAgent.setReportUncaughtExceptions(true);
    }

    public static IkeApplication getApp() {
        return application;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static boolean isLogin(boolean checkToLogin) {
        if (!User.isLogin && checkToLogin) {
            ARouterUtil.navigation(login);
            BaseUtil.toast("请登陆后再试");
        }
        return User.isLogin;
    }

    public static RadioApi getRadioApi() {
        return application.api;
    }

    public static User getUser() {
        return application.user;
    }

    private void initAppKey() {
        PlatformParams params = new PlatformParams("openPlatformConfig");
        params.setPlatform("weixin");
        api.getOpenPlatformConfig(params).compose(new RestfulTransformer<>()).subscribe();
    }
}
