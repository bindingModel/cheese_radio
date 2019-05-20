package com.cheese.radio.base.util;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.binding.model.util.BaseUtil;

public class SlidingGestureListener implements GestureDetector.OnGestureListener,View.OnTouchListener {
    private GestureDetector gestureDetector;

    public SlidingGestureListener(Context context) {
        this.gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 单击事件
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.performClick();
        gestureDetector.onTouchEvent(event);
        return false;
    }
}
