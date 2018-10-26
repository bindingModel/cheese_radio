package com.cheese.radio.ui;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;

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
import com.cheese.radio.ui.user.login.LoginActivity;
import com.cheese.radio.ui.user.login.params.PlatformParams;
import com.pgyersdk.crash.PgyCrashManager;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.StatusCode;

import java.util.Map;

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
        String wechat_AppID = application.getString(R.string.umeng_wechat_AppID);
        String wechat_AppSecret = application.getString(R.string.umeng_wechat_AppSecret);
        registerWX(wechat_AppID,wechat_AppSecret);
        //td
        String td_AppId = getResources().getString(R.string.td_app_id);
        String td_AppChannel = getResources().getString(R.string.td_app_channel);
        TCAgent.LOG_ON = true;
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

    public static RadioApi getApi() {
        return application.api;
    }

    public static void registerWX(String wechat_AppID,String wechat_AppSecret) {
        PlatformConfig.setWeixin(wechat_AppID, wechat_AppSecret);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(application, ""
                , "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
    }
    public static void logout(final SHARE_MEDIA platform) {
        UMShareAPI.get(application).deleteOauth(App.getCurrentActivity(), platform, new UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

}
