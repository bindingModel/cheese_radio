package com.cheese.radio.ui.home.page.entity;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.SpanSize;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ItemHomePageRecommandBinding;


/**
 * Created by 29283 on 2018/3/5.
 */
@ModelView(R.layout.item_home_page_recommand)
public class RecommandEntity extends ViewInflateRecycler<ItemHomePageRecommandBinding> implements SpanSize, GridInflate<ItemHomePageRecommandBinding> {
    /**
     * image : http://cheese-radio-1256030909.cos.ap-guangzhou.myqcloud.com/images/c7/c28/1622424e9c762.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDzLbkmgG9mDR0VpMufGguwldS4VknuIl8%26q-sign-time%3D1521024826%3B1522118831%26q-key-time%3D1521024826%3B1522118831%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D599d8ef7d988ba6db4a40dfd7e9cc0af617498c3
     * playCount : 0
     * subTitle : 三只小猪是兄弟,为抵抗大野狼而有不同的遭遇
     * location : PLAY
     * id : 5
     * title : 三只小猪
     */
//    @Inject RadioApi api;
    private String image;
    private int playCount;
    private String subTitle;
    private String location;
    private int id;
    private String title;
    private int index;
    private int spansize = 2;


    @Override
    public ItemHomePageRecommandBinding attachView(Context context, ViewGroup co, boolean attachToParent, ItemHomePageRecommandBinding binding) {
        super.attachView(context, co, attachToParent, binding);
        WindowManager wm = (WindowManager) App.getCurrentActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height = dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);//屏幕宽度(dp)
        int screenHeight = (int) (height / density);//屏幕高度(dp)
        int imageHeght = screenWidth * 170 / 125;
        int imageWidth = imageHeght;
        if (index == 1) {
            imageWidth = (screenWidth - 15) * 3;
            imageHeght = imageWidth * 149 / 327;

        }
        ImageView imageView = getDataBinding().imageView;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(imageWidth, imageHeght);
        imageView.setLayoutParams(params);
        return super.attachView(context, co, attachToParent, binding);

    }

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

    @Override
    public int getSpanSize() {
        return spansize;
    }

    public void onClick(View view) {
        ARouterUtil.itemNavigation(location, id);
    }

//    @Override
//    public int getModelIndex() {
//        return index;
//    }

    public void setIndex(int index) {
        this.index = index;
        this.spansize = (index + 1) * 2;

    }

    public int getRadius() {
        return 15;
    }


}