package com.cheese.radio.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;



public class StrongSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    public StrongSeekBar(Context context) {
        super(context);
    }

    public StrongSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrongSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }
}
