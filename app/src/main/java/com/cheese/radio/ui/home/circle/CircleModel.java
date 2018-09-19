package com.cheese.radio.ui.home.circle;


import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;

import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.FragmentHomeCircleBinding;
import com.cheese.radio.inject.api.ContentParams;
import com.cheese.radio.inject.api.RadioApi;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(R.layout.fragment_home_circle)
public class CircleModel extends RecyclerModel<CircleFragment, FragmentHomeCircleBinding, CircleDateEntity> {
    @Inject CircleModel() { }

    @Inject RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, CircleFragment circleFragment) {
        super.attachView(savedInstanceState, circleFragment);
        setRcHttp((offset1, refresh) -> api.getCircleDateList(new ContentParams("activity")).compose(new RestfulTransformer<>()));
    }

    /* @Override
    public void attachView(Bundle savedInstanceState, CircleFragment circleFragment) {
        super.attachView(savedInstanceState, circleFragment);
        WebView webView = getDataBinding().webView;
        String circleURL = Constant.circle_url;
        webView.loadUrl(circleURL);//加载url
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webSettings.setTextZoom(100);
        *//**
     * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
     * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
     * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
     * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
     *//*
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        *//**
     * 以下操作解决lollipop之后默认不允许混合模式，https当中不能加载http资源
     *//*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
    //!!
    }*/
}
