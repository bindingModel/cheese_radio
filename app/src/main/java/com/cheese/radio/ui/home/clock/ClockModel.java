package com.cheese.radio.ui.home.clock;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.request.RecyclerStatus;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.util.SlidingGestureListener;
import com.cheese.radio.databinding.FragmentHomeClock2Binding;
import com.cheese.radio.inject.api.ContentParams;
import com.cheese.radio.inject.api.RadioApi;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;

/**
 * Created by 29283 on 2018/3/13.
 */
//@ModelView(value = R.layout.fragment_home_clock, model = true)
@ModelView(value = R.layout.fragment_home_clock_2, model = true)
public class ClockModel extends RecyclerModel<ClockFragment, FragmentHomeClock2Binding, ClockEnrollEntity> {
    @Inject
    ClockModel() {
    }

    @Inject
    RadioApi api;
    private SlidingGestureListener gestureListener;

    @Override
    public void attachView(Bundle savedInstanceState, ClockFragment clockFragment) {
        super.attachView(savedInstanceState, clockFragment);

        initEntity();
        getDataBinding().webView.getSettings().setSupportZoom(false);//缩放
        getDataBinding().webView.getSettings().setBuiltInZoomControls(true);
        getDataBinding().webView.getSettings().setDisplayZoomControls(false);//不显示控制器
        getDataBinding().webView.getSettings().setUseWideViewPort(true);
        getDataBinding().webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        getDataBinding().webView.getSettings().setLoadWithOverviewMode(true);
        getDataBinding().webView.loadUrl("file:///android_res/mipmap/cheese_clock_2.jpg");
        gestureListener = new SlidingGestureListener(clockFragment.getContext()) {
            /**
             * 单击事件
             * @param e
             * @return
             * 640 2982
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float height = getDataBinding().webView.getContentHeight()
                        * getDataBinding().webView.getScale();
                float width = getDataBinding().webView.getWidth();
                float y = e.getY() + getDataBinding().webView.getScrollY();
                float x = e.getX();

                BaseUtil.toast(    "y" +  String.valueOf(y * 2982 / height)+"\nx"+x *640 /width
                );

                //获取的实际宽和高后，计算实际触点的位置（以比例的形式进行呈现）
                //还有一个问题，业务逻辑上的id是什么。
                return super.onSingleTapUp(e);
            }
        };
        getDataBinding().webView.setOnTouchListener(gestureListener);
       /* getDataBinding().recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getDataBinding().dragView.onDragView(0, dy);
            }
        });*/
    }

    private void initEntity() {
        setRcHttp((offset1, refresh) -> api.courseTypeList(new ContentParams("courseTypeList")).compose(new RestfulTransformer<>()));
        /*List<ClockEnrollEntity> entities = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            entities.add(new ClockEnrollEntity(i));
        }
        setRcHttp((offset1, refresh) -> Observable.just(entities));*/
    }

    public void onClick(View view) {
        ARouterUtil.navigation(enroll);
    }

    public void paySuccess() {
        onHttp(RecyclerStatus.resumeError);
    }

    public void refreshClock() {
        onHttp(RecyclerStatus.resumeError);
    }

    @Override
    public void onComplete() {
        super.onComplete();
//        getAdapter().notifyDataSetChanged();
    }
}
