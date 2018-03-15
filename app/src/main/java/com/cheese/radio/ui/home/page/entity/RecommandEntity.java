package com.cheese.radio.ui.home.page.entity;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.item_home_page_recommand)
public  class RecommandEntity extends ViewInflateRecycler implements SpanSize{
    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3
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
    private String id;
    private String title;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public int getSpanSize() {
        return 2;
    }
}