package com.cheese.radio.util.wx;

import android.content.Context;

import com.cheese.radio.R;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class wxUtil {

    /**
     * 在登录 分享 支付时 进行绑定
     * @param context 。。
     * @param wechat_AppID 微信的appId
     * @param wechat_AppSecret 微信的秘钥
     */

    public static void initAppId(Context context,String wechat_AppID, String wechat_AppSecret){
//        String wechat_AppID = getResources().getString(R.string.umeng_wechat_AppID);
//        String wechat_AppSecret = getResources().getString(R.string.wechat_AppSecret);
        PlatformConfig.setWeixin(wechat_AppID, wechat_AppSecret);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(context, "", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
    }
}
