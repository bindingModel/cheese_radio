package com.cheese.radio.ui.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivityWebviewBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

/**
 * @author 505062212
 * @create 2019/3/31.
 */
@ModelView(R.layout.activity_webview)
public class WebViewModel extends ViewModel<WebViewActivity, ActivityWebviewBinding> {
    @Inject
    WebViewModel() {
    }

    private String url;
    private String backPath;
    private Bundle backBundle;

    @Override
    public void attachView(Bundle savedInstanceState, WebViewActivity webViewActivity) {
        super.attachView(savedInstanceState, webViewActivity);
        url = webViewActivity.getIntent().getStringExtra(Constant.url);
        backPath = webViewActivity.getIntent().getStringExtra(Constant.back_path);
        backBundle = webViewActivity.getIntent().getBundleExtra(Constant.back_bundle);
        initWebView(getDataBinding().webView, url);
    }

    private void initWebView(WebView webView, String body) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webView.setWebViewClient(new ReloadWebViewClient());
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
//        webView.loadUrl(body);
//        webView.setWebChromeClient();
        MyBaseUtil.setWebView(webView, body,getT());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty(backPath)) {
            if (backBundle != null) ARouterUtil.navigation(backPath, backBundle);
            else ARouterUtil.navigation(backPath);
        }
    }

    public void onBackClick(View view) {
        getT().finish();
    }
}
