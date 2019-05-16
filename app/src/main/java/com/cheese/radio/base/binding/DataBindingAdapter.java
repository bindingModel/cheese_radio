package com.cheese.radio.base.binding;

import android.Manifest;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binding.model.App;
import com.binding.model.util.BaseUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cheese.radio.R;
import com.cheese.radio.base.glide.GlideBlurformation;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:51
 * modify developer：  admin
 * modify time：9:51
 * modify remark：
 *
 * @version 2.0
 */

public class DataBindingAdapter {

    @BindingAdapter({"android:background"})
    public static void setBackground(View view, String imageUrl) {
        Context mContext = view.getContext();
        Glide.with(mContext)
                .load(imageUrl)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) view.setBackground(resource);
                        else view.setBackgroundDrawable(resource);
                    }
                });
    }

    @BindingAdapter({"backgroundBlur"})
    public static void setBackgroundBlur(View view, String imageUrl) {
        Context mContext = view.getContext();
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new GlideBlurformation(view.getContext()));//转化为圆角????模糊

        Glide.with(mContext)
                .load(imageUrl)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) view.setBackground(resource);
                        else view.setBackgroundDrawable(resource);
                    }
                });
    }

    //    --------------------------ProgressBar------------------------------
    @BindingAdapter("android:secondaryProgress")
    public static void setSecondaryProgress(ProgressBar bar, int progress) {
        bar.setSecondaryProgress(progress);
    }

    //    --------------------------ImageView------------------------
    @BindingAdapter({"android:src"})
    public static void setImageDrawable(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
                .priority(Priority.HIGH);//优先级
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
//                .transform(new CircleCrop());//转化为圆角
        Glide.with(context)
                .load(imageUrl)
                .apply(options)
//                .placeholder(R.mipmap.img_default2_normal)
//                .error(R.mipmap.img_default2_normal)
                .into(imageView);
    }

    @BindingAdapter("circle")
    public static void setImageCircleDrawable(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new CircleCrop());//转化为圆角
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    //                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
    @BindingAdapter("head")
    public static void head(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .error(R.mipmap.logo)
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//缓存策略
                .transform(new CircleCrop());//转化为圆角
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    //                .placeholder(R.mipmap.ic_launcher)//预加载图片
//                .error(R.mipmap.ic_launcher)//加载失败显示图片
    @BindingAdapter({"android:src", "radius"})
    public static void setImageRadiusDrawable(ImageView imageView, String url, int radius) {
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        radius = (int) (radius * BaseUtil.getDisplayMetrics(context).density + 0.5f);
        RequestOptions options2 = new RequestOptions()
                .fitCenter()
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//缓存策略
                .transform(new RoundedCorners(radius));//转化为圆角
        Glide.with(context)
                .load(url)

                .apply(options2)
                .into(imageView);
    }


    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, @DrawableRes int mipmapId) {
        if (mipmapId != 0) view.setImageResource(mipmapId);
    }

    //    --------------------------TextView------------------------
    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, String image) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(image).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                Drawable[] drawables = view.getCompoundDrawables();
                view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
            }
        });
    }

    @BindingAdapter({"android:drawableLeft"})
    public static void setDrawableLeft(TextView view, String image) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(image).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                Drawable[] drawables = view.getCompoundDrawables();
                view.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
            }
        });
    }

    @BindingAdapter("alpha")
    public static void setAlpha(View view,float alpha){
        if(alpha>=0&&alpha<=1)view.setAlpha(alpha);
    }
    @BindingAdapter("class_head")
    public static void setClassHead(ImageView imageView,String url){
//        if(!Patterns.WEB_URL.matcher(url).matches())imageView.setImageBitmap();
        Context context = imageView.getContext();
        Glide.with(context).clear(imageView);
        RequestOptions options2 = new RequestOptions()
                .fitCenter()
                .priority(Priority.HIGH)//优先级
                .placeholder(R.mipmap.my_class)
                .error(R.mipmap.my_class)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
                .transform(new RoundedCorners(3));//转化为圆角
        Glide.with(context)
                .load(url)
                .apply(options2)
                .into(imageView);
    }

    @BindingAdapter({"android:text"})
    public static void setText(TextView textView, SpannableStringBuilder style) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(style);
    }
    @BindingAdapter("android:click")
    public static void setClickListener(View view, View.OnClickListener listener){
        view.setOnClickListener((v -> {

        }));
    }

    @BindingAdapter("android:onClick")
    public static void setOnclickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            private long lastTime = 0;
            @Override
            public void onClick(View v) {
                BaseUtil.checkPermission(App.getCurrentActivity(),(aBoolean -> {
                    long currTime = System.currentTimeMillis();
                    if (currTime - lastTime > 500) {
                        listener.onClick(v);
                    }
                    lastTime = currTime;
                }),Manifest.permission.READ_PHONE_STATE);
            }
        });
    }
//    @BindingAdapter("drawableLeft")
//    public static void setDrawableLeft(TextView view,String url){
//        Context mContext = view.getContext();
//        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                Drawable[] drawables = view.getCompoundDrawables();
//                view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
//            }
//        });
//
//           }

//    @BindingAdapter("background_blur")
//    public static void backgroundBlur(View view, String url) {
//        Context mContext = view.getContext();
////        Glide.with(mContext)
////                .load(url)
////                // 设置高斯模糊
////                .bitmapTransform(new BlurTransformation(this, 14, 3))
////                .into(allBg);
//        Glide.with(mContext)
//                .load(url)
//                .bitmapTransform(new BlurTransformation(mContext))
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            view.setBackground(resource);
//                        } else {
//                            view.setBackgroundDrawable(resource);
//                        }
//                    }
//                });
//    }


}
