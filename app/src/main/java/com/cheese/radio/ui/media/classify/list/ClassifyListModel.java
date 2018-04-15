package com.cheese.radio.ui.media.classify.list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

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
import com.cheese.radio.ui.user.my.favority.MyFavorityTitle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.activity_classify_list)
public class ClassifyListModel extends RecyclerModel<ClassifyListActivity, ActivityClassifyListBinding, Inflate> {

    @Inject
    ClassifyListModel() {
    }

    @Inject
    RadioApi api;
    private Integer tagId;
    private List<Inflate> list = new ArrayList<>();
    private final ClassifyListParams params = new ClassifyListParams("queryByTag");

    @Override
    public void attachView(Bundle savedInstanceState, ClassifyListActivity classifyListActivity) {
        super.attachView(savedInstanceState, classifyListActivity);
        String title = getT().getIntent().getStringExtra(Constant.title) != null ? getT().getIntent().getStringExtra(Constant.title) : "分类";
        getDataBinding().toolbarTitle.setText(title);
        getDataBinding().layoutRecycler.setVm(this);
        setEnable(false);
        setPageFlag(false);
        tagId = getT().getIntent().getIntExtra(Constant.id, 0);
        params.setTagId(tagId);
        params.setFilter("");
        setRcHttp((offset1, refresh) -> api.getQueryByTag(params).compose(new RestfulTransformer<>()).map(classifyListData -> {

                    if (classifyListData.getSingle().getList() != null) {
                        list.add(new MyFavorityTitle("故事", classifyListData.getSingle().getTotal()));
                        list.addAll(classifyListData.getSingle().getList());
                    }
                    if (classifyListData.getGroup().getList() != null) {
                        list.add(new MyFavorityTitle("专辑"));
                        list.addAll(classifyListData.getGroup().getList());
                    }
                    return list;
                }
        ));

    }

    public void onFinishClick(View view) {
        getT().finish();
    }
}
