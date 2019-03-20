package com.cheese.radio.base.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.OverScroller;

import timber.log.Timber;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-03-20
 * Time:   15:29
 */
public class NestedScrollView3 extends NestedScrollView {
    private OverScroller mScroller;
    private int mLastScrollerY;
    private int[] mScrollConsumed=new int[]{0,0};

    public NestedScrollView3(@NonNull Context context) {
        super(context);
        init();
    }

    public NestedScrollView3(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedScrollView3(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void fling(int velocityY) {
        if (this.getChildCount()>0){
            this.mLastScrollerY = this.getScrollY();
            this.startNestedScroll(2, 1);
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, velocityY, 0, 0, -2147483648, 2147483647, 0, 0);
            ViewCompat.postInvalidateOnAnimation(this);
        }
     /*   if (this.getChildCount() > 0) {
            this.startNestedScroll(2, 1);
            this.mScroller.fling(this.getScrollX(), this.getScrollY(), 0, velocityY, 0, 0, -2147483648, 2147483647, 0, 0);
            this.mLastScrollerY = this.getScrollY();
            ViewCompat.postInvalidateOnAnimation(this);
        }
*/
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (this.mScroller.computeScrollOffset()) {
            int x = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            Timber.v("y:"+y);
            int dy = y - this.mLastScrollerY;
            Timber.v("dy:"+dy);
            if (this.dispatchNestedPreScroll(0, dy, mScrollConsumed, (int[])null, 1)) {
                dy -= this.mScrollConsumed[1];
                Timber.v("dy:"+dy);
                this.dispatchNestedScroll(0, dy, 0, 0, (int[])null, 1);
            }
            this.mLastScrollerY = y;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void init() {
        this.mScroller = new OverScroller(this.getContext());
    }
}
