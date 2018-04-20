package com.cheese.radio.ui.media.classify;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.rxjava.RestfulZipTransformer;
import com.cheese.radio.databinding.ActivityClassifyBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.home.page.HomePageParams;
import com.cheese.radio.ui.home.page.RecommanData;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.activity_classify)
public class ClassifyModel extends RecyclerModel<ClassifyActivity, ActivityClassifyBinding, GridInflate> {

    @Inject
    ClassifyModel() {
    }

    @Inject
    RadioApi api;
    private final List<GridInflate> list = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, ClassifyActivity activity) {
        super.attachView(savedInstanceState, activity);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getT(), 18);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setPageFlag(false);
        setRcHttp((offset1, refresh) -> {
            if (refresh) {
                list.clear();
            }
            return api.getQueryCategroy(new ClassifyParams("queryCategroy"))
                    .compose(new RestfulTransformer<>())
                    .map(
                            classifyData -> {
                                for (ClassifyData data : classifyData) {
                                    list.add(data);
                                    list.addAll(data.getSubTagList());
                                }
                                return list;
                            }
                    );
        });
    }
}
