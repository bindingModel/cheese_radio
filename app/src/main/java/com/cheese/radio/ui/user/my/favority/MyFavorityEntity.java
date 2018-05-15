package com.cheese.radio.ui.user.my.favority;

import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.RecyclerInflate;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import java.util.List;

/**
 * Created by 29283 on 2018/4/10.
 */
@ModelView(R.layout.holder_classify_list)
public class MyFavorityEntity extends ViewInflateRecycler implements SpanSize, GridInflate {
    /**
     * image :     _400x400(2).jpg:/c13/c0/1626b2687b35b.jpg
     * playCount : 0
     * subTitle : 三只小猪是兄弟,为抵抗大野狼而有不同的遭遇
     * location : PLAY
     * id : 5
     * title : 三只小猪
     */

    private String image;
    private int playCount;
    private String subTitle;
    private String location;
    private int id;
    private String title;
    private int audioSize;
    private int seconds;

    public int getAudioSize() {
        return audioSize;
    }

    public String getAudioSizeString() {
        if(seconds==0)return "0个故事";
        return audioSize+"个故事";
    }

    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public int getSeconds() {
       return seconds;
    }

    public String getSecondsString() {
        if(seconds==0)return "00:00";
        return MyBaseUtil.getMinute(seconds);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlayCount() {
        return String.valueOf(playCount);
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRadius(){
        return 15;
    }

    public void onPlayClick(View view){
        ARouterUtil.itemNavigation(location,id);
    }
    @Override
    public int getSpanSize() {
         return 18;
    }
}

