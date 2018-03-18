package com.cheese.radio.ui.home.page.entity;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.play.PlayParams;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.anchors;
import static com.cheese.radio.inject.component.ActivityComponent.Router.classify;
import static com.cheese.radio.inject.component.ActivityComponent.Router.play;
import static com.cheese.radio.ui.Constant.AUTHOR_INFO;
import static com.cheese.radio.ui.Constant.AUTHOR_LIST;
import static com.cheese.radio.ui.Constant.CATEGORY_LIST;
import static com.cheese.radio.ui.Constant.CONTENT_LIST;
import static com.cheese.radio.ui.Constant.GROUP_INFO;
import static com.cheese.radio.ui.Constant.PLAY;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(value={R.layout.item_home_page_recommand,R.layout.item_home_page_sixth})
public  class RecommandEntity extends ViewInflateRecycler implements SpanSize, GridInflate {
    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3
     * playCount : 0
     * subTitle : 三只小猪是兄弟,为抵抗大野狼而有不同的遭遇
     * location : PLAY
     * id : 5
     * title : 三只小猪
     */
    @Inject RadioApi api;
    private String image;
    private int playCount;
    private String subTitle;
    private String location;
    private String id;
    private String title;
    private int index;
    private int spansize=2;
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
        return spansize;
    }

    public void onClick(View view){
        Bundle bundle=new Bundle();
        bundle.putString(Constant.location,location);
        bundle.putString(Constant.id,id);
        switch (location) {
            case CONTENT_LIST:
                ARouterUtil.navigation(play,bundle);
                break;
            case CATEGORY_LIST:
                ARouterUtil.navigation(classify);
                break;
            case AUTHOR_LIST:
                ARouterUtil.navigation(anchors);
                break;
            case AUTHOR_INFO:

                break;
            case GROUP_INFO:
                BaseUtil.toast("专辑详情");
//                ARouterUtil.navigation(play,bundle);
                break;
            case PLAY:
                BaseUtil.toast("跳转绘本");
                ARouterUtil.navigation(play,bundle);
                break;
        }
    }
    @Override
    public int getModelIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.spansize=(index+1)*2;
    }
}