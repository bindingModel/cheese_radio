package com.cheese.radio.ui.home.page;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.SpanSize;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.ui.home.page.entity.RecommandEntity;

import java.util.List;

import static com.binding.model.util.BaseUtil.T;

/**
 * Created by 29283 on 2018/3/15.
 */

@ModelView(R.layout.item_home_page_recommand_title)
public class RecommanData extends ViewInflateRecycler implements SpanSize {
    /**
     * subTitle : 今日热门推荐
     * locationId :
     * viewType : block
     * location :
     * title : 今日推荐
     * list : [{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3","playCount":0,"subTitle":"三只小猪是兄弟,为抵抗大野狼而有不同的遭遇","location":"PLAY","id":"5","title":"三只小猪"},{"image":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c28/c13/16224249baf28.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024806%3B1522118811%26q-key-time%3D1521024806%3B1522118811%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D0bbe7eededc3d23467169d31759d031470a04453","playCount":0,"subTitle":"小马独立思考,敢于探索的精神","location":"PLAY","id":"6","title":"小马过河"}]
     */

    private String subTitle;
    private String locationId;
    private String viewType;
    private String location;
    private String title;
    private List<RecommandEntity> list;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecommandEntity> getList() {
        return list;
    }

    public void setList(List<RecommandEntity> list) {
        this.list = list;
    }

    public CharSequence getMsg() {

        return BaseUtil.colorText(
                T(title, true, R.color.text_black, 1),
                T(subTitle, false, R.color.text_gray)
        );
    }
    @Override
    public int getSpanSize() {
        return 4;
    }
}
