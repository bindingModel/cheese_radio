package com.cheese.radio.ui.media.anchors;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.util.BaseUtil;
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

    private AnchorsParams params=new AnchorsParams();
    @Override
    public void attachView(Bundle savedInstanceState, AnchorsActivity anchorsActivity) {
        super.attachView(savedInstanceState, anchorsActivity);
        getDataBinding().layoutRecycler.setVm(this);
        /*setRcHttp((offset1, refresh) -> api.getAnchors(new AnchorsParams()).compose(new RestfulTransformer<>()).
                map(anchorsItems -> anchorsItems));*/
        BaseUtil.toast(params.toString());
        api.getAnchors(params).compose(new RestfulTransformer<>()).subscribe(anchorsItems -> {
         accept(anchorsItems);
    },BaseUtil::toast);

    }
}