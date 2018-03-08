package com.cheese.radio.ui.anchors;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityAnchorsBinding;
import com.cheese.radio.inject.api.IkeApi;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/8.
 */
@ModelView(R.layout.activity_anchors)
public class AnchorsModel extends RecyclerModel<AnchorsActivity,ActivityAnchorsBinding,AnchorsItem> {


    @Inject
    AnchorsModel() {
    }
    @Inject
    IkeApi api;
    @Override
    public void attachView(Bundle savedInstanceState, AnchorsActivity anchorsActivity) {
        super.attachView(savedInstanceState, anchorsActivity);
        getDataBinding().layoutRecycler.setVm(this);
        setRcHttp((offset1, refresh) -> api.getAnchors().compose(new RestfulTransformer<>()).
                map(anchorsItems -> anchorsItems));

    }
}
