package com.cheese.radio.ui.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.binding.model.adapter.AdapterType;
import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivitySearchBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.search.entity.HotSearchEntity;
import com.cheese.radio.ui.search.entity.HotSearchTitleEntity;
import com.cheese.radio.ui.search.params.HotSearchParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.internal.operators.observable.ObservableElementAt;

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
    private  List<GridInflate> list = new ArrayList<>();
    public ObservableBoolean cancelBoolean = new ObservableBoolean(false);
    public ObservableField<String> searchInput = new ObservableField<>("");
    private final HotSearchParams params = new HotSearchParams("hotsearch");
    private ObservableEmitter<String> emitter;
    private Observable<String> observable = io.reactivex.Observable.create(e -> this.emitter = e);

    @Override
    public void attachView(Bundle savedInstanceState, SearchActivity searchActivity) {
        super.attachView(savedInstanceState, searchActivity);
        getDataBinding().homeEdit.addTextChangedListener(this);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(searchActivity, 20);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setEnable(true);
        setPageFlag(true);
        setRoHttp(((offset1, refresh) -> {
//            hashMap.put("start", offset1);
//            hashMap.put("length", getPageCount());
            if (params.getTitle() != null) {
                params.setStartIndex(offset1);
                params.setMaxCount(getPageCount()/2);
            }
            return api.getHotSearch(params)
                    .compose(new RestfulTransformer<>()).map(hotSearchEntities  -> {

//                                list.addAll(hotSearchEntities);
                                if (params.getTitle()==null) {
                                    list.add(new HotSearchTitleEntity("热门搜索"));
                                } else {
                                    for (HotSearchEntity entity : hotSearchEntities) {
                                        entity.setIndex(1);
                                    }
                                }
                                list.addAll(hotSearchEntities);
                                return list;
                            }
                    );
        }));
        observable
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribe(s -> onHttp(3), BaseUtil::toast);
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

        if (text.length() < 1) {
//            getDataBinding().cancelButton.setVisibility(View.INVISIBLE);
//            getDataBinding().cancelButton.clearAnimation();
            params.setTitle(null);
            params.setMethod("hotsearch");
            cancelBoolean.set(false);
            onHttp(0, 3);
        }
//        } else getDataBinding().cancelButton.setVisibility(View.VISIBLE);
        else {

            params.setTitle(text);
            params.setMethod("search");
            cancelBoolean.set(true);
            emitter.onNext(s.toString());
        }


    }

    public void onCleanClick(View view) {
        searchInput.set("");
        params.setTitle("");
    }
    public void onFinishClick(View view){
        getT().finish();
    }
}
