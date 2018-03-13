package com.cheese.radio.ui.home.page;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.IkeApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.fragment_home_page)
public class HomePageModel extends RecyclerModel<HomePageFragment,FragmentHomePageBinding,GridInflate>{
    @Inject
    HomePageModel() {
    }

    @Inject
    IkeApi api;

    @Override
    public void attachView(Bundle savedInstanceState, HomePageFragment homePageFragment) {
        super.attachView(savedInstanceState, homePageFragment);
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(homePageFragment.getContext(), 4);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
//        setRoHttp((offset1, refresh) -> {
//            /*if (refresh < 1)return api.getProducts(offset1,getPageCount())
//                    .compose(new RestfulTransformer<>())
//                    .map(homePageData -> {
//                        List<GridInflate> list =new ArrayList<>();
//                        list.addAll(homePageData.getProducts());
//                        return list;
//                    });
//            else*/ return getZip(offset1,getPageCount());
//        });
    }
         private Observable<List<GridInflate>> getZip(int start, int length) {
        return null;
    }
    public void onSearchClick(View view){

    }
}
