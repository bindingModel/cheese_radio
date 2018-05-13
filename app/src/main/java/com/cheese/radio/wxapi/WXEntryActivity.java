package com.cheese.radio.wxapi;


import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * Created by 29283 on 2018/4/3.
 */

public class WXEntryActivity  extends WXCallbackActivity {
    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    Toast.makeText(this, "支付成功！", Toast.LENGTH_LONG).show();
                    break;
                case -2:
                    Toast.makeText(this,"支付取消！",Toast.LENGTH_LONG).show();
                    break;
                case -1:
                    Toast.makeText(this,"支付失败！",Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(this,"支付出错！",Toast.LENGTH_LONG).show();
                    break;
            }
        }
        super.onResp(resp);//一定要加super，实现我们的方法，否则不能回调
    }
}
