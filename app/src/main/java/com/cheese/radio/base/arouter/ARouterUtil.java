package com.cheese.radio.base.arouter;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binding.model.Config;
import com.cheese.radio.R;

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

}
