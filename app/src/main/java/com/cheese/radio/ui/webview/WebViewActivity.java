package com.cheese.radio.ui.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.util.MyWebChromeClient;

/**
 * @author 505062212
 * @create 2019/3/31.
 */
@Route(path = ActivityComponent.Router.webview)
public class WebViewActivity extends BaseActivity<WebViewModel> implements MyWebChromeClient.WebCall {
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
    public ValueCallback<Uri> mUploadMessage;
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != RESULT_OK ? null
                    : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;

        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (null == mUploadMessageForAndroid5)
                return;
            Uri result = (intent == null || resultCode != RESULT_OK) ? null
                    : intent.getData();
            if (result != null) {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[] { result });
            } else {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[] {});
            }
            mUploadMessageForAndroid5 = null;
        }

    }

    @Override
    public void fileChose(ValueCallback<Uri> uploadMsg) {
        openFileChooserImpl(uploadMsg);
    }

    @Override
    public void fileChose5(ValueCallback<Uri[]> uploadMsg) {
        openFileChooserImplForAndroid5(uploadMsg);
    }

    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"),
                FILECHOOSER_RESULTCODE);
    }

    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("*/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");

        startActivityForResult(chooserIntent,
                FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

}
