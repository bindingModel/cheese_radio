package com.cheese.radio.ui.webview;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-04-03
 * Time:   13:42
 */
public class ReloadWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = view.getUrl();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!TextUtils.isEmpty(url) && url.equals(request.getUrl().toString()))
                view.reload();
            else
                view.loadUrl(url);
            return true;
        }
        return false;
    }

    /**
     * @param view
     * @param url
     * @deprecated
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!TextUtils.isEmpty(view.getUrl()) && view.getUrl().equals(url))
            view.reload();
        else
            view.loadUrl(url);
        return true;
    }
}
