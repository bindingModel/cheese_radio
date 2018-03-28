package com.cheese.radio.ui.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivitySearchBinding;
import com.cheese.radio.inject.api.RadioApi;
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
public class SearchModel extends RecyclerModel<SearchActivity, ActivitySearchBinding, GridInflate> implements TextWatcher {

    @Inject
    SearchModel() {
    }

    @Inject
    RadioApi api;
    private final List<GridInflate> list = new ArrayList<>();
    public ObservableBoolean cancelBoolean = new ObservableBoolean(false);
    public ObservableField<String> searchInput = new ObservableField<>("");
    private final HotSearchParams params = new HotSearchParams("hotsearch");

    @Override
    public void attachView(Bundle savedInstanceState, SearchActivity searchActivity) {
        super.attachView(savedInstanceState, searchActivity);
        getDataBinding().homeEdit.addTextChangedListener(this);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(searchActivity, 18);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setEnable(false);
        setPageFlag(false);
        setRcHttp(((offset1, refresh) -> {
//            hashMap.put("start", offset1);
//            hashMap.put("length", getPageCount());
                if(params.getTitle()!=null) {
                    params.setStartIndex(offset1);
                    params.setMaxCount(getPageCount());
                }
            return api.getHotSearch(params)
                    .compose(new RestfulTransformer<>()).map(
                            hotList -> {
                                list.add(new HotSearchTitleEntity("热门搜索"));
                                if (hotList.size() < 5) {
                                    list.addAll(hotList);
                                } else {
                                    list.addAll(hotList.subList(0, 5));
                                    hotList = hotList.subList(5, hotList.size());
                                    for (HotSearchEntity entity : hotList) {
                                        entity.setIndex(1);
                                    }
                                    list.add(new HotSearchEntity("热门搜索", 1));
                                    list.addAll(hotList);
                                }
                                return list;
                            }
                    );
        }));
    }

    public void onSearchClick(View view) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        params.setTitle(text);
        if (text.length() < 1) {
//            getDataBinding().cancelButton.setVisibility(View.INVISIBLE);
//            getDataBinding().cancelButton.clearAnimation();
            cancelBoolean.set(false);
        }
//        } else getDataBinding().cancelButton.setVisibility(View.VISIBLE);
        cancelBoolean.set(true);
        setPageFlag(true);

    }

    public void onCleanClick(View view) {
        searchInput.set("");
        params.setTitle("");
    }
}
