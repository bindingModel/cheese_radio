package com.cheese.radio.ui.home.circle;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentHomeCircleBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(R.layout.fragment_home_circle)
public class CircleModel extends ViewModel<CircleFragment, FragmentHomeCircleBinding> {
    @Inject
    CircleModel() {
    }
    private  String circleURL="http://h5.zhishidiantai.com/zhishidiantai/activity.html";
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    public void attachView(Bundle savedInstanceState, CircleFragment circleFragment) {
        super.attachView(savedInstanceState, circleFragment);
        webView = getDataBinding().webView;
//        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
        webView.loadUrl(circleURL);//加载url

        //       使用webview显示html代码
//        webView.loadDataWithBaseURL(null,descriptionText.get(), "text/html" , "utf-8", null);

        //  webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
//        webView.setWebChromeClient(webChromeClient);
//        webView.setWebViewClient(webViewClient);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
               return true;
            }
        });
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);

    }
}
