package com.cheese.radio.base.arouter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binding.model.App;
import com.binding.model.Config;
import com.cheese.radio.R;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;



/**
 * Created by arvin on 2017/12/6.
 */

public class ARouterUtil {

    public static void navigation(String url, Bundle bundle) {
        build(url, bundle).navigation();
    }

    public static void navigation(String url) {
        navigation(url, "");
    }

    public static void navigation(Uri url) {
        ARouter.getInstance().build(url).navigation();
    }

    public static void navigationWeb(String url,String title){
        Bundle bundle = new Bundle();
        bundle.putString(Config.title,title);
        bundle.putString(Constant.url,url);
        ARouterUtil.navigation(ActivityComponent.Router.webview,bundle);
    }

    public static void navigationBack(String url, Bundle bundle, Context context, NavigationCallback callback) {
        build(url, bundle).navigation(App.getCurrentActivity(),callback);

    }


    private static Postcard build(String url, Bundle bundle) {
        return ARouter.getInstance()
                .build(url)
                .withTransition(R.anim.push_right_in, R.anim.push_right_out)
                .with(bundle);
    }

//    public static void navigationUrl(String url) {
//        ARouter.getInstance()
//                .build(ActivityComponent.Router.web_view)
//                .withTransition(R.anim.push_right_in, R.anim.push_right_out)
//                .withString(Config.path, url)
//                .navigation();
//    }

    public static void navigation(String path, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.title,title);
        navigation(path, bundle);
    }

    public static void itemNavigation(String location,int id){
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.id,id);
        String path = ActivityComponent.Router.cheese+location
                .toLowerCase()
                .replace("_info","")
                .replace("_list","s");
        ARouterUtil.navigation(path,bundle);
    }
    public static void itemNavigation(String location,int id,String tagName){
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.id,id);
        bundle.putString(Constant.title,tagName);
        String path = ActivityComponent.Router.cheese+location
                .toLowerCase()
                .replace("_info","")
                .replace("_list","s");
        ARouterUtil.navigation(path,bundle);
    }
    public static void LocationNavigation(String location,Bundle bundle){
        String path = ActivityComponent.Router.cheese+location
                .toLowerCase()
                .replace("_info","")
                .replace("_list","s");
        ARouterUtil.navigation(path,bundle);
    }
}
