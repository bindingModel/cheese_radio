package com.cheese.radio.ui.search.entity;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/22.
 */
@ModelView(value = {R.layout.item_hot_search, R.layout.search_text})
public class HotSearchEntity extends ViewInflateRecycler implements SpanSize, GridInflate {

    public HotSearchEntity(String title,Integer index) {
        this.title = title;
        this.index=index;
    }

    public HotSearchEntity() {
    }

    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c4/c31/162242634d24.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024911%3B1522118916%26q-key-time%3D1521024911%3B1522118916%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3De05cdf562d26e7399b27189907ba047c36d8be9e
     * playCount : 10
     * subTitle : 乌龟和兔子赛跑
     * location : PLAY
     * id : 2
     * title : 龟兔赛跑
     * favorCount : 11
     * url : http://111.231.237.11:8083/docroot/attachments/audio.mp3
     */


    private String image;
    private int playCount;
    private String subTitle;
    private String location;
    private int id;
    private String title;
    private int favorCount;
    private String url;
    private Integer index=0;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPlayCount() {
        return playCount;
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

    public int getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(int favorCount) {
        this.favorCount = favorCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getSpanSize() {
//        if (this.getHolder_position() < 5)
          if(this.index==0) return 5;
        else return 18;
    }

    @Override
    public int getModelIndex() {
      if(this.index==0)
        return 0;
      else return 1;
    }
}
