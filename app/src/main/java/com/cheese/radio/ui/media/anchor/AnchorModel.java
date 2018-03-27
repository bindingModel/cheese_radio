package com.cheese.radio.ui.media.anchor;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Model;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityAnchorBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.entity.AnchorEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(value = R.layout.activity_anchor, model = true)
public class AnchorModel extends PagerModel<AnchorActivity, ActivityAnchorBinding, AnchorEntity> {

    @Inject
    AnchorModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    @Inject
    RadioApi api;
    private Integer authorId;
    private AnchorParams params;
    private final List<AnchorEntity> list = new ArrayList<>();
    private AnchorData anchorData;

    @Override
    public void attachView(Bundle savedInstanceState, AnchorActivity anchorActivity) {
        super.attachView(savedInstanceState, anchorActivity);
        authorId = getT().getIntent().getIntExtra(Constant.authorId, 0);
        params = new AnchorParams("info", authorId);
        addDisposable(api.getAuthor(params).compose(new RestfulTransformer<>()).subscribe(anchorData -> {
            getDataBinding().setEntity(anchorData);
            setFragment(anchorData);
        }));
    }

    public void setFragment(AnchorData anchorData) {
        list.add(new AnchorEntity(anchorData));
        list.add(new AnchorEntity(anchorData));
        try {
            accept(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
