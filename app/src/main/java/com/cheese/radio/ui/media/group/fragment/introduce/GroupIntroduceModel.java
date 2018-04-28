package com.cheese.radio.ui.media.group.fragment.introduce;

import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentGroupBriefIntroductionBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.group.GroupInfoModel;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_group_brief_introduction)
public class GroupIntroduceModel extends ViewModel<GroupIntroduceFragment,FragmentGroupBriefIntroductionBinding>{
    @Inject GroupIntroduceModel(){}
    private WebView webView;
    private ProgressBar progressBar;
    public ObservableField<String> description=new ObservableField<>();
    @Override

    public void attachView(Bundle savedInstanceState, GroupIntroduceFragment groupIntroduceFragment) {
        super.attachView(savedInstanceState, groupIntroduceFragment);
        Bundle bundle=groupIntroduceFragment.getArguments();
        if (bundle!=null){
            description.set(bundle.getString(Constant.description));
        }
        progressBar= getDataBinding().progressbar;//进度条

        webView = getDataBinding().webview;
//        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
     //   webView.loadUrl("http://139.196.35.30:8080/OkHttpTest/apppackage/test.html");//加载url

 //       使用webview显示html代码
        webView.loadDataWithBaseURL(null,description.get(), "text/html" , "utf-8", null);

      //  webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
//        webView.setWebChromeClient(webChromeClient);
//        webView.setWebViewClient(webViewClient);

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSettings.setTextZoom(16);

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
