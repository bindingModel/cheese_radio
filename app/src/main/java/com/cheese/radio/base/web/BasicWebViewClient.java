package com.cheese.radio.base.web;


import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.ClientCertRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cheese.radio.base.util.CertifiUtils;
import com.cheese.radio.base.util.Xutils;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.IkeApplication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;

import timber.log.Timber;

public class BasicWebViewClient extends WebViewClient {
    private X509Certificate[] certificatesChain;
    private PrivateKey clientCertPrivateKey;
    private SSLContext sslContext;

    public BasicWebViewClient() {
        initPrivateKeyAndX509Certificate(IkeApplication.getApp());
        sslContext = Xutils.getSSLContext(IkeApplication.getApp());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        if ((null != clientCertPrivateKey) && ((null != certificatesChain) && (certificatesChain.length != 0))) {
            request.proceed(clientCertPrivateKey, certificatesChain);
        } else {
            request.cancel();
        }
    }

    private static final String KEY_STORE_CLIENT_PATH = "client.p12";//客户端要给服务器端认证的证书
    private static final String KEY_STORE_PASSWORD = "btydbg2018";// 客户端证书密码

    private void initPrivateKeyAndX509Certificate(Context context) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            InputStream ksIn = context.getResources().getAssets().open(KEY_STORE_CLIENT_PATH);
            keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
            Enumeration<?> localEnumeration;
            localEnumeration = keyStore.aliases();
            while (localEnumeration.hasMoreElements()) {
                String str3 = (String) localEnumeration.nextElement();
                clientCertPrivateKey = (PrivateKey) keyStore.getKey(str3, KEY_STORE_PASSWORD.toCharArray());
                if (clientCertPrivateKey == null) {
                    continue;
                } else {
                    Certificate[] arrayOfCertificate = keyStore.getCertificateChain(str3);
                    certificatesChain = new X509Certificate[arrayOfCertificate.length];
                    for (int j = 0; j < certificatesChain.length; j++) {
                        certificatesChain[j] = ((X509Certificate) arrayOfCertificate[j]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceivedSslError(final WebView view, SslErrorHandler handler, SslError error) {
        CertifiUtils.OnCertificateOfVerification(handler,view.getUrl() );
//        handler.proceed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    //下面处理图片
    @Override
    public WebResourceResponse shouldInterceptRequest(final WebView view, String url) {
        return processRequest(Uri.parse(url));
    }

    @Override
    @TargetApi(21)
    public WebResourceResponse shouldInterceptRequest(final WebView view, WebResourceRequest interceptedRequest) {
        return processRequest(interceptedRequest.getUrl());
    }

    private WebResourceResponse processRequest(Uri uri) {
        Timber.d("SSL_PINNING_WEBVIEWS,GET:%1s " , uri.toString());
        try { // 定义获取的资源流，资源类型，资源编码
            InputStream is;
            String contentType;
            String encoding;
            // 设置一个url链接
            URL url = new URL(uri.toString());
            // 如果是http请求，这里如果加载的是http请求，则也要建立HttpURLConnection而不是默认加载。
            if (uri.toString().startsWith(Constant.CONFIGURATION_HTTP)) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                is = httpURLConnection.getInputStream();
                contentType = httpURLConnection.getContentType();
                encoding = httpURLConnection.getContentEncoding();
            } else { // 如果是https请求
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                is = urlConnection.getInputStream();
                contentType = urlConnection.getContentType();
                encoding = urlConnection.getContentEncoding();
            }
            // 如果信息头中的资源类型不为null,则继续
            if (contentType != null) {
                String mimeType = contentType;
                // 解析出mimeType
                if (contentType.contains(";")) {
                    mimeType = contentType.split(";")[0].trim();
                }
                Timber.d("SSL_PINNING_WEBVIEWS", "Mime: " + mimeType);
                // 返回设置重新设置过的请求
                return new WebResourceResponse(mimeType, encoding, is);
            }
        } catch (SSLHandshakeException e) {
            e.printStackTrace();
            Timber.d("SSL_PINNING_WEBVIEWS", e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Timber.d("SSL_PINNING_WEBVIEWS", e.getLocalizedMessage());
        } // 返回一个空的请求
        return new WebResourceResponse(null, null, null);
    }


}
