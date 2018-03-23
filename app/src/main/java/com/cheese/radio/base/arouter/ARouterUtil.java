package com.cheese.radio.base.arouter;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.binding.model.Config;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import static com.cheese.radio.inject.component.ActivityComponent.Router.anchor;
import static com.cheese.radio.inject.component.ActivityComponent.Router.anchors;
import static com.cheese.radio.inject.component.ActivityComponent.Router.classify;
import static com.cheese.radio.inject.component.ActivityComponent.Router.groupInfo;
import static com.cheese.radio.inject.component.ActivityComponent.Router.play;
import static com.cheese.radio.ui.Constant.AUTHOR_INFO;
import static com.cheese.radio.ui.Constant.AUTHOR_LIST;
import static com.cheese.radio.ui.Constant.CATEGORY_LIST;
import static com.cheese.radio.ui.Constant.CONTENT_LIST;
import static com.cheese.radio.ui.Constant.GROUP_INFO;
import static com.cheese.radio.ui.Constant.PLAY;

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

    public static void itemNavigation(String location,String id){
        Bundle bundle = new Bundle();
        bundle.putString(Constant.id,id);
        String path = ActivityComponent.Router.cheese+location
                .toLowerCase()
                .replace("_info","")
                .replace("_list","s");
        ARouterUtil.navigation(path,bundle);


//        Bundle bundle=new Bundle();
//        bundle.putString(Constant.id,id);
//        BaseUtil.toast("location:"+location);
//        switch (location) {
//            case CONTENT_LIST:
//                break;
//            case CATEGORY_LIST:
//                ARouterUtil.navigation(classify);
//                break;
//            case AUTHOR_LIST:
//                ARouterUtil.navigation(anchors);
//                break;
//            case AUTHOR_INFO:
//                ARouterUtil.navigation(anchor);
//                break;
//            case GROUP_INFO:
//                ARouterUtil.navigation(groupInfo,bundle);
//                break;
//            case PLAY:
//                ARouterUtil.navigation(play,bundle);
//                break;
//        }

    }
}
