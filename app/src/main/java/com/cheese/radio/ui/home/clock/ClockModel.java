package com.cheese.radio.ui.home.clock;

import android.graphics.RectF;
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

import java.util.ArrayList;
import java.util.List;

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
    private List<ClockEnrollEntity> list = new ArrayList<>();

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
            //            private float dx = 101,dy = 960;
//            private RectF btnA1 = new RectF(101, 960, 209, 1115);
//            private RectF btnA2 = new RectF(237, 960, 360, 1115);
//            private RectF btnA3 = new RectF(400, 960, 533, 1115);
//
//            private RectF btnB1 = new RectF(101, 1572, 209, 1725);
//            private RectF btnB2 = new RectF(237, 1572, 360, 1725);
//            private RectF btnB3 = new RectF(400, 1572, 533, 1725);
//
//            private RectF btnC1 = new RectF(101, 2235, 209, 2379);
//            private RectF btnC2 = new RectF(237, 2235, 360, 2379);
//            private RectF btnC3 = new RectF(400, 2235, 533, 2379);
            private RectF[] btnArray = new RectF[]{
                    new RectF(101, 960, 209, 1115),
                    new RectF(237, 960, 360, 1115),
                    new RectF(400, 960, 533, 1115),
                    new RectF(101, 1572, 209, 1725),
                    new RectF(237, 1572, 360, 1725),
                    new RectF(400, 1572, 533, 1725),
                    new RectF(101, 2235, 209, 2379),
                    new RectF(237, 2235, 360, 2379),
                    new RectF(400, 2235, 533, 2379)
            };

            /**
             * 单击事件
             * @param e
             * @return
             * 640 2982
             * --------
             * 101 960 209 1115 //237 xxx 360 xxx//400 xxx 533 xxx
             * xxx 1572 xxx 1725// xxx xxx xxx xxx //xxx xxx xxx xxx
             * xxx 2235 xxx 2379//
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float height = getDataBinding().webView.getContentHeight()
                        * getDataBinding().webView.getScale();
                float width = getDataBinding().webView.getWidth();
                float scale = 2982 / height;
                float y = (e.getY() + getDataBinding().webView.getScrollY()) * scale;
                float x = e.getX() * scale;

//                BaseUtil.toast("y" + String.valueOf(y + "\nx" + x));
                for (int i = 0; i < btnArray.length; i++) {
                    if (btnArray[i].contains(x, y)) {
                        int position = i / 3;
                        if (position>=list.size()){
                            BaseUtil.toast("该课程暂停服务");
                            break;
                        }
                        ClockEnrollEntity entity = list.get(position);
                        switch (i % 3) {
                            case 0:
//                                BaseUtil.toast("详情");
                                entity.onInfoClick(null);
                                break;
                            case 1:
//                                BaseUtil.toast("报名");
                                entity.onEnrollClick(null);
                                break;
                            case 2:
//                                BaseUtil.toast("预约");
                                entity.onBookClick(null);
                                break;
                        }
                        break;
                    }
                }

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
        addDisposable(api.courseTypeList(new ContentParams("courseTypeList")).compose(new RestfulTransformer<>())
                .subscribe(it -> list = it, BaseUtil::toast));
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
