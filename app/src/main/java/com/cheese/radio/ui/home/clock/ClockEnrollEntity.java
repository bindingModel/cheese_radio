package com.cheese.radio.ui.home.clock;

import android.graphics.drawable.Drawable;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

@ModelView(R.layout.holder_clock_enroll)
public class ClockEnrollEntity extends ViewInflateRecycler {
    private int position;

    public ClockEnrollEntity(int position) {
        this.position = position;
    }

    public String getPosition() {
        return "0" + (position + 1);
    }

    public String getTitle() {
        switch (position) {
            case 0:return "一元体验课";
            case 1:return "标准课";
            case 2:return "芝士妈妈课";
            case 3:return "趣声社";
            case 4:return "声宝课";
            default:return "未知项目";
        }
    }
    public Drawable getTitleBg(){
        switch (position){
            case 0:return App.getDrawable(R.mipmap.tyk);
            case 1:return App.getDrawable(R.mipmap.bzk);
            case 2:return App.getDrawable(R.mipmap.zsk);
            case 3:return App.getDrawable(R.mipmap.qs);
            case 4:return App.getDrawable(R.mipmap.sbk);
            default:return null;
        }
    }
}
