package com.cheese.radio.ui.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.service.AudioServiceUtil;

import static com.cheese.radio.ui.Constant.BUTTON_CANCEL_ID;
import static com.cheese.radio.ui.Constant.BUTTON_NEXT_ID;
import static com.cheese.radio.ui.Constant.BUTTON_PALY_ID;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;

public class MusicBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioServiceUtil util=AudioServiceUtil.getInstance();
      int tag=  intent.getIntExtra(INTENT_BUTTONID_TAG,0);
      if(tag==BUTTON_PALY_ID ){
          if(util.isPlaying())util.pause();
//          else util.play();
          util.getNotManager().cancel(234);
      }
      else if(tag ==BUTTON_NEXT_ID ){

      }
      else if(tag==BUTTON_CANCEL_ID){
         util.getNotManager().cancel(234);
      }
        Model.dispatchModel("upDataButton");
//       BaseUtil.toast("收到广播："+tag);
    }


}
