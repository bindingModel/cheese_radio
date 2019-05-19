package com.cheese.radio.base.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.binding.model.App;

/**
 * @author sureping
 * @create 19-5-19.
 */
public class ViewUtil {
    /**
     *
     * @param view
     * @param index : 0 的时候为正方形 ，否则为长方形
     */
    public static void measureHomeRecommandEntity(View view,int index){
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
        int imageHeght =(int)(width/2-15*density);
        int imageWidth = imageHeght;
        if (index == 1) {
            imageWidth =(int)(width-15*density);
            imageHeght = imageWidth * 124 / 284;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(imageWidth, imageHeght);
        view.setLayoutParams(params);
    }
}
