package com.cheese.radio.base.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.base.view
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/13 5:07 PM
 * @change
 * @chang time
 * @class describe
 */
public class StrongFrameLayout extends FrameLayout {
    public StrongFrameLayout(Context context) {
        super(context);
    }

    public StrongFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrongFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StrongFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }
}
