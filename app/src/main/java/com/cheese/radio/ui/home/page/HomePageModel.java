package com.cheese.radio.ui.home.page;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.InfoEntity;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.base.rxjava.RestfulZipTransformer;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.page.entity.CategoryEntity;
import com.cheese.radio.ui.home.page.entity.RecommandEntity;
import com.cheese.radio.ui.media.play.PlayParams;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.fragment_home_page)
public class HomePageModel extends RecyclerModel<HomePageFragment,FragmentHomePageBinding,GridInflate>{
    @Inject
    HomePageModel() {
    }

    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, HomePageFragment homePageFragment) {
        super.attachView(savedInstanceState, homePageFragment);
        IkeApplication.isLogin();
        getDataBinding().layoutRecycler.setVm(this);
        GridLayoutManager layoutManager = new GridLayoutManager(homePageFragment.getContext(), 4);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        setEnable(false);
        setPageFlag(false);
        setRoHttp((offset1, refresh) -> {
            return getZip();
        });
    }
    private Observable<List<GridInflate>> getZip() {
//        Observable<InfoEntity<List<CategoryEntity>>> categoriy = api.getGroupInfo(new PlayParams("contentInfo","123")).compose(new RestfulZipTransformer<>());
        Observable<InfoEntity<List<CategoryEntity>>> categoriy = api.getCategoriy(new HomePageParams("category")).compose(new RestfulZipTransformer<>());
        Observable<InfoEntity<List<RecommanData>>> recommandList = api.getRecommand(new HomePageParams("recommandList")).compose(new RestfulZipTransformer<>());
        return Observable.zip(categoriy, recommandList, (cate, entity) -> {
            List<GridInflate> list = new ArrayList<>();
            if (cate.getCode() == 0 && cate.getData() != null &&  !cate.getData().isEmpty()) {
                list.addAll(cate.getData().subList(0,4));
            }
            if(entity.getCode()==0 &&entity.getData()!=null &&!entity.getData().isEmpty()){
                for (RecommanData data:entity.getData()) {
                    list.add(data);
                    if(data.getViewType().equals("list"))
                        for (RecommandEntity recommandEntity :data.getList() ) {
                            recommandEntity.setIndex(1);
                        }
                    list.addAll(data.getList());
                }

            }

            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void onSearchClick(View view){

    }
}
