package com.cheese.radio.ui.media.classify;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityClassifyBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/17.
 * 分类
 */
@ModelView(R.layout.activity_classify)
public class ClassifyModel extends RecyclerModel<ClassifyActivity, ActivityClassifyBinding, GridInflate> {

    @Inject
    ClassifyModel() {
    }

    @Inject
    RadioApi api;
    private final List<GridInflate> list = new ArrayList<>();
    private Integer id =null;
    @Override
    public void attachView(Bundle savedInstanceState, ClassifyActivity activity) {
        super.attachView(savedInstanceState, activity);
//        getDataBinding().layoutRecycler.setVm(this);
        int id = activity.getIntent().getIntExtra(Constant.id, 0);
        if (id!=0){
            this.id=id;
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getT(), 18);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setPageFlag(false);
        setRcHttp((offset1, refresh) -> {
            if (refresh) {
                list.clear();
            }
            ClassifyParams params = new ClassifyParams("queryCategroy");
            params.setId(this.id);
            return api.getQueryCategroy(params)
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
