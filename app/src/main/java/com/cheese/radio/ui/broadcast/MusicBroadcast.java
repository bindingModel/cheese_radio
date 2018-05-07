package com.cheese.radio.ui.broadcast;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.binding.model.util.BaseUtil;
import com.cheese.radio.ui.service.AudioServiceUtil;

import static com.cheese.radio.ui.Constant.BUTTON_PALY_ID;
import static com.cheese.radio.ui.Constant.BUTTON_PAUSE_ID;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;

public class MusicBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioServiceUtil util=AudioServiceUtil.getInstance();
      int tag=  intent.getIntExtra(INTENT_BUTTONID_TAG,0);
      if(tag==BUTTON_PALY_ID && !util.isPlaying()){
          util.play();
      }
      else if(tag ==BUTTON_PAUSE_ID && util.isPlaying()){
          util.pause();
      }
       BaseUtil.toast("收到广播："+tag);
    }
}
