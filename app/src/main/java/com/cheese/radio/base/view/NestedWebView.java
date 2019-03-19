package com.cheese.radio.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.webkit.WebView;

import timber.log.Timber;

/**
 * @name cheese_radio
 * @anthor bangbang QQ:740090077
 * @time 2019/3/19 7:18 PM
 * 只有编译器可能不骗你。
 */
public class NestedWebView extends WebView implements NestedScrollingChild {
    private boolean canScrollVertically = true;
    private VelocityTracker mVelocityTracker;
    private int mScrollPointerId;

    /**
     * Construct a new WebView with a Context object.
     *
     * @param context A Context object used to access application assets.
     */
    public NestedWebView(Context context) {
        super(context);
    }

    /**
     * Construct a new WebView with layout parameters.
     *
     * @param context A Context object used to access application assets.
     * @param attrs   An AttributeSet passed to our parent.
     */
    public NestedWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Construct a new WebView with layout parameters and a default style.
     *
     * @param context      A Context object used to access application assets.
     * @param attrs        An AttributeSet passed to our parent.
     * @param defStyleAttr
     */
    public NestedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NestedWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Timber.w("l:%1s, t:%2s, oldl:%3s, oldt:%4s", l, t, oldl, oldt);
    }

    private float touchX, touchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                touchX = (event.getX() + 0.5F);
                touchY = (event.getY() + 0.5F);
                this.getScrollingChildHelper().setNestedScrollingEnabled(true);
                this.getScrollingChildHelper().startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                this.mScrollPointerId = event.getPointerId(0);
                this.mVelocityTracker.addMovement(event);
                Timber.v("-this.mVelocityTracker.getYVelocity(this.mScrollPointerId) " + -this.mVelocityTracker.getYVelocity(this.mScrollPointerId));

                float x = (event.getX() + 0.5F);
                float y = (event.getY() + 0.5F);
                int dx = (int) (touchX - x);
                int dy = (int) (touchY - y);
                this.getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, new int[]{0, 0}, new int[]{0, 0});
                break;
            case MotionEvent.ACTION_UP:
                this.mVelocityTracker.computeCurrentVelocity(1000);
                float xvel = 0;
//                canScrollHorizontally ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0F;
                float yvel = canScrollVertically ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0F;
                this.fling((int) xvel, (int) yvel);
                mVelocityTracker.recycle();
            case MotionEvent.ACTION_POINTER_UP:
                this.getScrollingChildHelper().stopNestedScroll();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void fling(int xvel, int yvel) {
        Timber.v("xvel%1s, yvel%2s", xvel, yvel);
        if (!dispatchNestedPreFling(xvel, yvel)) {
            dispatchNestedFling(xvel, yvel, false);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private boolean isScrollToBottom() {
        return (int) (getContentHeight() * getScale() - (getHeight() + getScrollY())) == 0;
    }


    private NestedScrollingChildHelper mScrollingChildHelper;

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }

        return this.mScrollingChildHelper;
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.getScrollingChildHelper().isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int axes) {
        return this.getScrollingChildHelper().startNestedScroll(axes);
    }

    public boolean startNestedScroll(int axes, int type) {
        return this.getScrollingChildHelper().startNestedScroll(axes, type);
    }

    public void stopNestedScroll() {
        this.getScrollingChildHelper().stopNestedScroll();
    }

    public void stopNestedScroll(int type) {
        this.getScrollingChildHelper().stopNestedScroll(type);
    }

    public boolean hasNestedScrollingParent() {
        return this.getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasNestedScrollingParent(int type) {
        return this.getScrollingChildHelper().hasNestedScrollingParent(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return this.getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed,
                                           int[] offsetInWindow) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed,
                                           int[] offsetInWindow, int type) {
        return this.getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public void flingScroll(int vx, int vy) {
        super.flingScroll(vx, vy);
    }
}

