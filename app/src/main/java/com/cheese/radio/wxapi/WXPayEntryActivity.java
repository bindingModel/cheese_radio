package com.cheese.radio.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;

import com.umeng.socialize.weixin.view.WXCallbackActivity;



/**
 * Created by arvin on 2018/1/19.
 */

public class WXPayEntryActivity extends BaseActivity<WXPayEntryModel> implements IWXAPIEventHandler {
    private final String TAG = WXCallbackActivity.class.getSimpleName();
    protected UMWXHandler mWxHandler = null;
    private IWXAPI iwxapi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UMShareAPI umShareAPI = UMShareAPI.get(this.getApplicationContext());
        Log.e("","WXCallbackActivity");
        this.mWxHandler = (UMWXHandler) umShareAPI.getHandler(SHARE_MEDIA.WEIXIN);
        Log.e(this.TAG, "handleid=" + this.mWxHandler);
        this.mWxHandler.onCreate(this.getApplicationContext(), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        this.handleIntent(this.getIntent());
        iwxapi = WXAPIFactory.createWXAPI(this, getString(R.string.wechat_AppID));
        iwxapi.handleIntent(getIntent(), this);
    }

    protected void handleIntent(Intent intent) {
        this.mWxHandler.getWXApi().handleIntent(intent, this);
    }

    protected void onNewIntent(Intent paramIntent) {
        Log.d(this.TAG, "### WXCallbackActivity   onNewIntent");
        super.onNewIntent(paramIntent);
        this.setIntent(paramIntent);
        UMShareAPI api = UMShareAPI.get(this.getApplicationContext());
        this.mWxHandler = (UMWXHandler) api.getHandler(SHARE_MEDIA.WEIXIN);
        Log.e(this.TAG, "handleid=" + this.mWxHandler);
        this.mWxHandler.onCreate(this.getApplicationContext(), PlatformConfig.getPlatform(SHARE_MEDIA.WEIXIN));
        this.handleIntent(paramIntent);
        setIntent(paramIntent);
        iwxapi.handleIntent(paramIntent, this);
    }

    public void onResp(BaseResp resp) {
        String msg = "";
        if (this.mWxHandler != null && resp != null) {
            try {
                this.mWxHandler.getWXEventHandler().onResp(resp);
                if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

                    switch (resp.errCode) {
                        case 0:
                            Model.dispatchModel("paySuccess");
                            msg = "支付成功！";
                            break;
                        case -1:
                            msg = "支付失败！";
                            break;
                        case -2:
                            msg = "支付取消！";
                            break;
                        default:
                            msg = "支付出错";
                            break;
                    }
                }
            } catch (Exception var3) {
            }
        }
        BaseUtil.toast(msg);
        this.finish();
    }


//    @Override
//    public void onResp(BaseResp resp) {
//        String msg;
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            switch (resp.errCode) {
//                case 0:msg = "支付成功！";break;
//                case -1:msg = "支付失败！";break;
//                case -2:msg = "支付取消！";break;
//                default:msg = "支付出错";break;
//            }
//            if(Config.Constant.payEntity!=null)
//                vm.setEntity(Config.Constant.payEntity);
//            BaseUtil.toast(this, msg);
//            if (resp.errCode != 0) finish();
//        } else {
//            super.onResp(resp);
//        }
//    }


    public void onReq(BaseReq req) {
        if (this.mWxHandler != null) {
            this.mWxHandler.getWXEventHandler().onReq(req);
        }
        this.finish();
    }
}
