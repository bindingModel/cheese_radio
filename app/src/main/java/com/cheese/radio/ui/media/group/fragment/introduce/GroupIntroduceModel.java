package com.cheese.radio.ui.media.group.fragment.introduce;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentGroupBriefIntroductionBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_group_brief_introduction)
public class GroupIntroduceModel extends ViewModel<GroupIntroduceFragment, FragmentGroupBriefIntroductionBinding> {
    @Inject
    GroupIntroduceModel() {
    }

    private WebView webView;
    private ProgressBar progressBar;
    public ObservableField<String> description = new ObservableField<>();

    @Override

    public void attachView(Bundle savedInstanceState, GroupIntroduceFragment groupIntroduceFragment) {
        super.attachView(savedInstanceState, groupIntroduceFragment);
        Bundle bundle = groupIntroduceFragment.getArguments();
        if (bundle != null) {
            description.set(bundle.getString(Constant.description));
        }
        progressBar = getDataBinding().progressbar;//进度条

        webView = getDataBinding().webview;
        MyBaseUtil.setWebView(webView,description.get());
    }
}
