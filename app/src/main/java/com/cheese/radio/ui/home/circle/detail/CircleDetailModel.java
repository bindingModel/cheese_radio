package com.cheese.radio.ui.home.circle.detail;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebView;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityCircleDetailBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

@ModelView(R.layout.activity_circle_detail)
public class CircleDetailModel extends ViewModel<CircleDetailActivity, ActivityCircleDetailBinding> {
    private String h5code;
    private String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/base.css\" type=\"text/css\" /> ";

    @Inject
    CircleDetailModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, CircleDetailActivity activity) {
        super.attachView(savedInstanceState, activity);
        h5code = activity.getIntent().getStringExtra(Constant.h5code);
//        css =getJson(activity,"circle.css");
        String body = "<html><header></header>" + h5code
                + "</body></html>";
        initWebView(getDataBinding().webView, body);
    }

    private void initWebView(WebView webView, String body) {
        MyBaseUtil.setWebView(webView,body);
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
}
