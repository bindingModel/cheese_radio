package com.cheese.radio.ui.home.page.banner;

import android.text.TextUtils;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.Model;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.ui.Constant;

@ModelView(value = {R.layout.holder_home_page_banner,R.layout.holder_circle})
public class HomePageBannerEntity extends ViewInflateRecycler {
    /**
     * brief :
     * name : 测试通栏
     * id : 7
     * pic : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c3/c4/169b8af81fa47.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1553581705%3B3131504905%26q-key-time%3D1553581705%3B3131504905%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3Df32db64934a3d115f366d9e6252ff9caad077143
     * type :
     * url : https://www.baidu.com
     */

    private String brief;
    private String name;
    private String id;
    private String pic;
    private String type;
    private String url;
    private String location;
    private String target_id;

    public void onImageClick(View view){
        if(!TextUtils.isEmpty(location)){
            if(Constant.ACTIVITY_LIST.equals(location)) {
                Model.dispatchModel(Constant.setCurrentItem,2);
            }
            else ARouterUtil.itemNavigation(location,Integer.parseInt(target_id),name);
        }else{
            ARouterUtil.navigationWeb(url,"");
        }
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRadius(){
        return 10;
    }
}
