package com.cheese.radio.ui.media.item;

import android.view.View;

import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.ui.service.AudioServiceUtil;

/**
 * Created by 29283 on 2018/3/21.
 */

@ModelView(R.layout.item_play)
public class PlayItem {
    AudioServiceUtil util =AudioServiceUtil.getInstance();


    public void onPlayClick(View view){
     boolean b= util.isPlaying()?util.pause():util.play();
    }
}
