package com.cheese.radio.base.view.recycle;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import timber.log.Timber;

/**
 * @name qiqi_android
 * @anthor bangbang QQ:740090077
 * @time 2019/3/9 2:49 PM
 * 只有编译器可能不骗你。
 */
public class PagerLayoutManager extends LinearLayoutManager {
    private float MILLISECONDS_PER_INCH = 25.0F;
    private PagerSnapHelper2 pagerSnapHelper = null;

    public PagerLayoutManager(Context context) {
        this(context, LinearLayoutManager.VERTICAL, false);
    }

    public PagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public PagerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        init();
        pagerSnapHelper.attachToRecyclerView(view);
    }

    private void init() {
        if (pagerSnapHelper == null) {
            pagerSnapHelper = new PagerSnapHelper2();
        }
    }

    public void setOnPageListener(PagerSnapHelper2.OnPageListener onPageListener) {
        init();
        this.pagerSnapHelper.setOnPageListener(onPageListener);
    }

    /**
     * 处理水平滚动的数据
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        Timber.w("dx:%1s,state:%2s", dx, state);
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    static class PagerSnapHelper2 extends PagerSnapHelper {
        private OnPageListener onPageListener = (fromPosition, toPosition) -> { };
        private int currentPosition = 0;
        private RecyclerView recyclerView;

        @Override
        public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
            super.attachToRecyclerView(recyclerView);
            this.recyclerView = recyclerView;
        }

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            if (recyclerView.getAdapter()==null)return position;
            if (position < recyclerView.getAdapter().getItemCount()) {
                onPageListener.onPageSelector(currentPosition, position);
                currentPosition = position;
            }
            return position;
        }

        public void setOnPageListener(@NonNull OnPageListener onPageListener) {
            this.onPageListener = onPageListener;
        }

        public interface OnPageListener {
            public void onPageSelector(int fromPosition, int toPosition);
        }

    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        return PagerLayoutManager.this
                                .computeScrollVectorForPosition(targetPosition);
                    }
                    @Override
                    protected float calculateSpeedPerPixel
                    (DisplayMetrics displayMetrics) {
                        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                    }

                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public void setMILLISECONDS_PER_INCH(float MILLISECONDS_PER_INCH) {
        this.MILLISECONDS_PER_INCH = MILLISECONDS_PER_INCH;
    }
}
