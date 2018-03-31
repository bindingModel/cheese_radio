package com.cheese.radio.ui.media.classify.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityClassifyListBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.classify.ClassifyData;
import com.cheese.radio.ui.media.classify.ClassifyParams;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.activity_classify_list)
public class ClassifyListModel extends RecyclerModel<ClassifyListActivity,ActivityClassifyListBinding,Inflate>{

    @Inject ClassifyListModel(){}
    @Inject RadioApi api;
    private Integer tagId;
    private final ClassifyListParams params=new ClassifyListParams("queryByTag");
    @Override
    public void attachView(Bundle savedInstanceState, ClassifyListActivity classifyListActivity) {
        super.attachView(savedInstanceState, classifyListActivity);
        getDataBinding().layoutRecycler.setVm(this);
        tagId=getT().getIntent().getIntExtra(Constant.id,0);
        params.setTagId(tagId);
        params.setFilter("");
        api.getQueryByTag(params).compose(new RestfulTransformer<>()).subscribe(classifyData -> {
//            for (ClassifyData data:classifyData) {
//                list.add(data);
//                list.addAll(data.getSubTagList());
//            }
//            accept(list);
        }, BaseUtil::toast);
    }
}
