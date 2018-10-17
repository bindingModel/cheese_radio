package com.cheese.radio.ui.user.service.center;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityServiceCenterBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_service_center)
public class CenterModel extends ViewHttpModel<CenterActivity, ActivityServiceCenterBinding, Object> {

    @Inject
    CenterModel() {
    }


    @Override
    public void accept(Object o) throws Exception {

    }

    @Override
    public void attachView(Bundle savedInstanceState, CenterActivity centerActivity) {
        super.attachView(savedInstanceState, centerActivity);
        ImageView imageView = getDataBinding().adImage;
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
        int imageWidth = (int) (width);
        int imageHeght = (int) imageWidth * 2 / 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageWidth, imageHeght);
        imageView.setLayoutParams(params);
    }
}
