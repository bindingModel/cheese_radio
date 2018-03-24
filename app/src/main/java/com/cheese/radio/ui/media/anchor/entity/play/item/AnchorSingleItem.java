package com.cheese.radio.ui.media.anchor.entity.play.item;

import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;

/**
 * Created by 29283 on 2018/3/16.
 */
@ModelView(R.layout.item_anchor_single)
public class AnchorSingleItem extends ViewInflateRecycler {
    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c10/c8/1622425615656.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024853%3B1522118858%26q-key-time%3D1521024853%3B1522118858%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Dec060efc36502c1c88f514275bcd29d7cfdfa5b0
     * playCount : 110
     * subTitle : 老鼠偷米，快来抓
     * id : 3
     * title : 老鼠偷米
     */

    private String image;
    private int playCount;
    private String subTitle;
    private int id;
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

    public String getPlayCountString(){
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

    public void onPlayClick(View view){
        ARouterUtil.itemNavigation("play",id);
    }
}
