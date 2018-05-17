package com.cheese.radio.ui.media.anchor.entity.description;

import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentAnchorMsgBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.group.fragment.introduce.GroupIntroduceFragment;
import com.cheese.radio.util.MyBaseUtil;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.fragment_anchor_msg)
public class DescriptionFragmentModel extends ViewModel<DescriptionFragment,FragmentAnchorMsgBinding> {
    private WebView webView;
    private ProgressBar progressBar;
    @Inject DescriptionFragmentModel(){}
    public ObservableField<String> descriptionText = new ObservableField<>();
    @Override
    public void attachView(Bundle savedInstanceState, DescriptionFragment fragment) {
        super.attachView(savedInstanceState, fragment);
        Bundle bundle=fragment.getArguments();
        if (bundle!=null){
            descriptionText.set(bundle.getString(Constant.description));
        }
        webView = getDataBinding().webview;
        MyBaseUtil.setWebView(webView,descriptionText.get());
    }

}
