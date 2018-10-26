package com.cheese.radio.ui.user.my.course;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binding.model.App;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Model;
import com.cheese.radio.R;
import com.cheese.radio.base.binding.DataBindingAdapter;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.view.recycle.HeadDecoration;
import com.cheese.radio.databinding.ActivityMyCourseBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.home.HomeModel;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by 29283 on 2018/3/30.
 */
@ModelView(R.layout.activity_my_course)
public class MyCourseModel extends RecyclerModel<MyCourseActivity, ActivityMyCourseBinding, MyCourseEntity> {
    @Inject
    MyCourseModel() {
    }

    @Inject
    RadioApi api;
    private int courseTypeId;
    private MyCourseParams myCourseParams = new MyCourseParams();
    private View defView;

    @Override
    public void attachView(Bundle savedInstanceState, MyCourseActivity myCourseActivity) {
        super.attachView(savedInstanceState, myCourseActivity);
        getDataBinding().layoutRecycler.setVm(this);
        initDef();
        courseTypeId = myCourseActivity.getIntent().getIntExtra(Constant.courseTypeId, -1);
        if (courseTypeId == -1) {
            myCourseParams.setMethod("myClass2");
            setRcHttp(((offset1, refresh) -> api.myClass2(myCourseParams).compose(new RestfulTransformer<>())));
        } else {
            initRecycle();
            myCourseParams.setMethod("myClassInfo");
            myCourseParams.setCourseTypeId(courseTypeId);
            setRcHttp((offset1, refresh) -> api.myClassInfo(myCourseParams).compose(new RestfulTransformer<>()));
        }
//        api.getMyCourse(new MyCourseParams("myClass")).compose(new RestfulTransformer<>()).subscribe(list -> {
//           accept(list.getAlready());
//        });
    }

    /**
     * 生成一个头
     */
    private void initRecycle() {
        HeadDecoration headDecoration = new HeadDecoration() {
            @Override
            public String getHeaderName(int var1) {
                try {
                    if (var1 == -1) return null;
                    MyCourseEntity inflate = getAdapter().getList().get(var1);
                    String state = inflate.getCourseState();
                    if ("已完课".equals(state))return "已完成课程";
                    else return "已预约课程";
                } catch (Exception e) {
                    Timber.e(e);
                }
                return null;
            }
        };
        headDecoration.setHeaderHeight((int) App.dipTopx(24));
        headDecoration.setTextSize((int) App.dipTopx(12));
        headDecoration.setTextColor(App.getColor(R.color.head_gray));
        getDataBinding().layoutRecycler.recyclerView.addItemDecoration(headDecoration);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        afterCompleteAndError();
    }

    @Override
    public void onThrowable(Throwable throwable) {
        super.onThrowable(throwable);
        afterCompleteAndError();
    }

    private void afterCompleteAndError() {
        if (getAdapter().size() == 0) {
            getDataBinding().layoutRecycler.swipeRefreshLayout.setVisibility(View.GONE);
            defView.setVisibility(View.VISIBLE);
        }
    }

    private void initDef() {
        if (defView != null) return;
        defView = LayoutInflater.from(getT()).inflate(R.layout.default_page, getDataBinding().rootView, false);
        ImageView imageView = defView.findViewById(R.id.imageView);
        imageView.setImageDrawable(App.getDrawable(R.mipmap.nopic));
        TextView textView = defView.findViewById(R.id.textView);
        DataBindingAdapter.setText(textView, getTouchText());
        getDataBinding().rootView.addView(defView, 1);
        TextView touchGo = defView.findViewById(R.id.touch_go);
        touchGo.setOnClickListener(v -> {
            Model.dispatchModel("setCurrentItem", 1);
            this.finish();
        });
        defView.setVisibility(View.GONE);
    }

    /**
     * 错误和没有上过课的文本显示
     *
     * @return
     */
    public SpannableStringBuilder getTouchText() {
        String msg = "还没上过课？快快预约吧！";
        SpannableStringBuilder touchText = new SpannableStringBuilder();
        touchText.append(msg);
        ClickableSpan createClick = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Model.dispatchModel("setCurrentItem", 1);
                App.finish(MyCourseActivity.class);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
//                    ds.setColor(App.getColor(R.color.text_red));
                ds.setUnderlineText(false);
            }
        };
        String touchContent = String.valueOf("快快预约吧！");
        int index = msg.indexOf(touchContent);
        touchText.setSpan(createClick, index, index + touchContent.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return touchText;
    }
}
