package com.cheese.radio.ui.home.circle.detail;

import android.content.Context;
import android.content.res.AssetManager;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCircleDetailBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.home.circle.DateDetailParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@ModelView(R.layout.activity_circle_detail)
public class CircleDetailModel extends ViewModel<CircleDetailActivity, ActivityCircleDetailBinding> {
    private String h5code;
    private String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/base.css\" type=\"text/css\" /> ";
    private int detailId = 0;
    private String method;
    public ObservableField<String> detailTitle = new ObservableField<>();
    public ObservableField<String> detailImage = new ObservableField<>();
    public ObservableBoolean canShowBtn = new ObservableBoolean(false);
    @Inject
    RadioApi api;

    @Inject
    CircleDetailModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, CircleDetailActivity activity) {
        super.attachView(savedInstanceState, activity);
        detailId = activity.getIntent().getIntExtra(Constant.id, 0);
        method = activity.getIntent().getStringExtra(Constant.method);
        if (detailId != 0)
            initApiInfo();
        else {
            BaseUtil.toast("没有具体内容");
            finish();
        }
//        h5code = activity.getIntent().getStringExtra(Constant.h5code);
//        css =getJson(activity,"circle.css");

    }

    private void initApiInfo() {
        switch (method) {
            case Constant.activityInfo:
                activityInfo();
                break;
            case Constant.courseTypeInfo:
                courseTypeInfo();
                    break;
            default:
                break;
        }
    }

    private void courseTypeInfo() {
        DateDetailParams params =new DateDetailParams(method);
        params.setId(detailId);
        Disposable disposable =api.courseTypeInfo(params)
                .compose(new RestfulTransformer<>())
                .subscribe(entity->{
                    h5code = "<html><header>" + css + "</header>" + entity.getDesc()
                            + "</body></html>";
                    detailTitle.set(entity.getName());
                    initWebView(getDataBinding().webView, h5code);
                },BaseUtil::toast);
    }

    private void activityInfo() {
        DateDetailParams params = new DateDetailParams(method);
        params.setId(detailId);
        Disposable disposable = api.getCircleDateDetail(params)
                .compose(new RestfulTransformer<>())
                .subscribe(entity -> {
                    canShowBtn.set(entity.getAllowSign() == 1);
                    detailImage.set(entity.getImg());
                    h5code = "<html><header>" + css + "</header>" + entity.getContent()
                            + "</body></html>";
                    detailTitle.set(entity.getTitle());
                    initWebView(getDataBinding().webView, h5code);

                }, BaseUtil::toast);
    }

    private void initWebView(WebView webView, String body) {
        webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
//        MyBaseUtil.setWebView(webView, body);
    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void onEnrollClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.id, detailId);
        ARouterUtil.navigation(ActivityComponent.Router.join, bundle);
    }
}
