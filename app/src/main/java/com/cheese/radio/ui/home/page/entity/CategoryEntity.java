package com.cheese.radio.ui.home.page.entity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import java.util.Arrays;
import java.util.List;


import static com.cheese.radio.ui.Constant.AUTHOR_INFO;
import static com.cheese.radio.ui.Constant.AUTHOR_LIST;
import static com.cheese.radio.ui.Constant.CATEGORY_LIST;
import static com.cheese.radio.ui.Constant.CONTENT_LIST;
import static com.cheese.radio.ui.Constant.GROUP_INFO;
import static com.cheese.radio.ui.Constant.PLAY;
import static com.cheese.radio.ui.Constant.id;

/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.item_home_page_category)
public class CategoryEntity extends ViewInflateRecycler implements SpanSize, GridInflate {

    /**
     * code : 0
     * data : [{"tagId":1,"icon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c13/c3/1622420c5a834.png?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024551%3B1522118555%26q-key-time%3D1521024551%3B1522118555%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D5ce83d9f2a517f3d833cf67c203d7498599ff542","location":"CONTENT_LIST","tagName":"最新"},{"tagId":2,"icon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c14/c26/1622420aacc63.png?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024544%3B1522118549%26q-key-time%3D1521024544%3B1522118549%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D55ed1bbf92b2bb467a9e10b8777eee82d673a93f","location":"AUTHOR_LIST","tagName":"主播圈"},{"tagId":3,"icon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c20/c28/16224208cd334.png?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024536%3B1522118541%26q-key-time%3D1521024536%3B1522118541%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D066a5085bf032d6254182bede5db0c3e96b0ae35","location":"CATEGORY_LIST","tagName":"伴睡"},{"tagId":4,"icon":"http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c9/c24/16224205d4b48.png?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024526%3B1522118531%26q-key-time%3D1521024526%3B1522118531%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D7b84632e728399a21a772ffa07214816e3d89c07","location":"CATEGORY_LIST","tagName":"分类"}]
     */


    /**
     * tagId : 1
     * icon : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c13/c3/1622420c5a834.png?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024551%3B1522118555%26q-key-time%3D1521024551%3B1522118555%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D5ce83d9f2a517f3d833cf67c203d7498599ff542
     * location : CONTENT_LIST
     * tagName : 最新
     */

    private int tagId;
    private String icon;
    private String location;
    private String tagName;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public int getSpanSize() {
        return 1;
    }

    public Drawable getThumbImage() {
        switch (getHolder_position()) {
            case 0:
                return App.getDrawable(R.mipmap.news);
            case 1:
                return App.getDrawable(R.mipmap.anchors);
            case 2:
                return App.getDrawable(R.mipmap.sleep);
            default:
                return App.getDrawable(R.mipmap.classify);

        }


        /* if(TextUtils.isEmpty(thumb_image)){
            if(!children.isEmpty())thumb_image = children.get(0).getThumb_image();
        }
        return thumb_image;*/
    }

    public void onClick(View view) {
        ARouterUtil.itemNavigation(location,tagId);
    }
}
