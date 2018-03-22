package com.cheese.radio.ui.search;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.cycle.Container;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivitySearchBinding;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.home.page.HomePageFragment;
import com.cheese.radio.ui.media.classify.ClassifyData;
import com.cheese.radio.ui.search.entity.HotSearchEntity;
import com.cheese.radio.ui.search.entity.HotSearchTitleEntity;
import com.cheese.radio.ui.search.params.HotSearchParams;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.activity_search)
public class SearchModel extends RecyclerModel<SearchActivity, ActivitySearchBinding, GridInflate> {

    @Inject
    SearchModel() {
    }

    @Inject
    RadioApi api;
    private final List<GridInflate> list = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, SearchActivity searchActivity) {
        super.attachView(savedInstanceState, searchActivity);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(searchActivity, 18);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        api.getHotSearch(new HotSearchParams("hotsearch")).compose(new RestfulTransformer<>()).subscribe(hotList -> {
            list.add(new HotSearchTitleEntity("热门搜索"));
            if (hotList.size() < 5) {
                list.addAll(hotList);
            } else {
                list.addAll(hotList.subList(0, 5));
                hotList = hotList.subList(5, hotList.size());
                for (HotSearchEntity entity : hotList) {
                    entity.setIndex(1);
                }
                list.add(new HotSearchEntity("热门搜索",1));
                list.addAll(hotList);
            }
            accept(list);
        }, BaseUtil::toast);
    }

    public void onSearchClick(View view) {

    }
}
