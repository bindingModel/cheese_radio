package com.cheese.radio.inject.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import timber.log.Timber;

/**
 * Created by apple on 2017/7/30.
 */
@Interceptor(priority = 100)
public class RouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        callback.onContinue(postcard);
//        boolean valid;
//        switch (postcard.getGroup()) {
//            case Constant.user:
//                valid = User.doLogin(postcard.getPath(), postcard.getExtras(), s -> callback.onContinue(postcard));
//                Timber.i("user:"+valid);
//                break;
//            case Constant.gateWay:
//                valid = User.validGateWay(postcard.getPath(), postcard.getExtras(), s -> callback.onContinue(postcard));
//                Timber.i("gateWay:"+valid);
//                break;
//            case Constant.room:
//                valid= User.validRoom(postcard.getPath(), postcard.getExtras(), s -> callback.onContinue(postcard));
//                Timber.i("room:"+valid);
//                break;
//            case Constant.device:
//                valid = User.validDevice(postcard.getPath(), postcard.getExtras(), s -> callback.onContinue(postcard));
//                Timber.i("device:"+valid);
//                break;
//            default:
//                callback.onContinue(postcard);
//                break;
//        }
    }

    @Override
    public void init(Context context) {
        Timber.i("init router interceptor");
    }
}
