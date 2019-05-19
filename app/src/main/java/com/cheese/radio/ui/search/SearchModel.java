package com.cheese.radio.ui.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.model.request.RecyclerStatus;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.util.LoadHelper;
import com.cheese.radio.databinding.ActivitySearchBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.search.entity.HotSearchTitleEntity;
import com.cheese.radio.ui.search.params.HotSearchParams;
import com.cheese.radio.ui.user.my.favority.MyFavorityEntity;
import com.cheese.radio.util.MyBaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.activity_search)
public class SearchModel extends RecyclerModel<SearchActivity, ActivitySearchBinding, GridInflate> implements TextWatcher, TextView.OnEditorActionListener {

    @Inject
    SearchModel() {
    }

    @Inject
    RadioApi api;

    public ObservableBoolean cancelBoolean = new ObservableBoolean(false);
    public ObservableField<String> searchInput = new ObservableField<>("");
    private final HotSearchParams params = new HotSearchParams("hotsearch");
    private ObservableEmitter<String> emitter;
    private Observable<String> observable = Observable.create(e -> this.emitter = e);
    private final List<GridInflate> list = new ArrayList<>();

    private LoadHelper loadHelper;
    private int spanSize = 20;

    @Override
    public void attachView(Bundle savedInstanceState, SearchActivity searchActivity) {
        super.attachView(savedInstanceState, searchActivity);
        initLoadHelper(searchActivity);

        getDataBinding().homeEdit.addTextChangedListener(this);
        getDataBinding().homeEdit.setOnEditorActionListener(this);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(searchActivity, spanSize);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setPageFlag(false);
        setRoHttp(((offset1, refresh) -> {
//            if (refresh != 0) {
//                list.clear();
//            }
            if (params.getTitle() != null) {
                params.setStartIndex(offset1);
                params.setMaxCount(getPageCount() / 2);
                return api.getSearch(params)
                        .compose(new RestfulTransformer<>())
                        .map(it -> {
                                    for (MyFavorityEntity entity : it.getSingle().getList()) {
                                        entity.setModelIndex(1);
                                    }
                                    for (MyFavorityEntity entity : it.getGroup().getList()) {
                                        entity.setModelIndex(1);
                                    }
                                    list.clear();
                                    if (!it.getSingle().getList().isEmpty()){
                                        list.add(new HotSearchTitleEntity("单曲"));
                                        list.addAll(it.getSingle().getList());
                                    }
                                    if (!it.getGroup().getList().isEmpty()){
                                        list.add(new HotSearchTitleEntity("专辑"));
                                        list.addAll(it.getGroup().getList());
                                    }
                                    return list;
                                }
                        );
            } else return api.getHotSearch(params)
                    .compose(new RestfulTransformer<>())
                    .flatMap(Observable::fromIterable)
                    .doOnNext(it -> it.setSpansize(spanSize / 2))
                    .toList().toObservable()
                    .map(hotSearchEntities -> {
                                list.clear();
                                list.add(new HotSearchTitleEntity("热门搜索"));
                                list.addAll(hotSearchEntities);
                                return list;
                            }

                    );
        }));
        Disposable subscribe = observable.debounce(2000, TimeUnit.MILLISECONDS)
                .subscribe(s -> onHttp(RecyclerStatus.resumeError), BaseUtil::toast);
    }

    private void initLoadHelper(AppCompatActivity activity) {
        loadHelper = new LoadHelper<>(activity);
        loadHelper.init(getDataBinding().content);
    }

    public void onSearchClick(View view) {
        onHttp(view);
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
        list.clear();
        if (text.length() < 1) {
//            getDataBinding().cancelButton.setVisibility(View.INVISIBLE);
//            getDataBinding().cancelButton.clearAnimation();
            params.setTitle(null);
            params.setMethod("hotsearch");
            cancelBoolean.set(false);
            setPageFlag(false);
            list.clear();
        }
//        } else getDataBinding().cancelButton.setVisibility(View.VISIBLE);
        else {

            setPageFlag(true);
            params.setTitle(text);
            params.setMethod("search");
            cancelBoolean.set(true);

        }
        emitter.onNext(s.toString());

    }

    public void onCleanClick(View view) {
        searchInput.set("");
        params.setTitle("");
    }

    public void onFinishClick(View view) {
        getT().finish();
    }


    /**
     * Called when an action is being performed.
     *
     * @param v        The view that was clicked.
     * @param actionId Identifier of the action.  This will be either the
     *                 identifier you supplied, or {@link EditorInfo#IME_NULL
     *                 EditorInfo.IME_NULL} if being called due to the enter key
     *                 being pressed.
     * @param event    If triggered by an enter key, this is the event;
     *                 otherwise, this is null.
     * @return Return true if you have consumed the action, else false.
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            MyBaseUtil.HideKeyboard(v);
        }
        return true;
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (getAdapter().getList().size() == 0)
            loadHelper.setLoadView(R.layout.item_search_result_empty);
        else loadHelper.onCancel();
    }
}
