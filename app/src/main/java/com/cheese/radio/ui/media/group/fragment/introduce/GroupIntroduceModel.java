package com.cheese.radio.ui.media.group.fragment.introduce;

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

    public String url ="";

    @Override

    public void attachView(Bundle savedInstanceState, GroupIntroduceFragment groupIntroduceFragment) {
        super.attachView(savedInstanceState, groupIntroduceFragment);
        Bundle bundle = groupIntroduceFragment.getArguments();
        if (bundle != null) {
            url=bundle.getString(Constant.description);
        }
        getDataBinding().webview.loadData(url,"text/html", "utf-8");
//        webView = getDataBinding().webview;
//        webView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
//        MyBaseUtil.setWebView(webView,url);
    }
}
