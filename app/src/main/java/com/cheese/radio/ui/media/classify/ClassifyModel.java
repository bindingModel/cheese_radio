package com.cheese.radio.ui.media.classify;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.binding.model.adapter.recycler.GridSpanSizeLookup;
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
    private final List<GridInflate> list=new ArrayList<>();
    @Override
    public void attachView(Bundle savedInstanceState, ClassifyActivity activity) {
        super.attachView(savedInstanceState, activity);
        GridLayoutManager layoutManager = new GridLayoutManager(getT(), 3);
        layoutManager.setSpanSizeLookup(new GridSpanSizeLookup<>(getAdapter()));
        setLayoutManager(layoutManager);
        api.getQueryCategroy(new ClassifyParams("queryCategroy")).compose(new RestfulTransformer<>()).subscribe(classifyData -> {
            for (ClassifyData data:classifyData) {
                list.add(data);
                list.addAll(data.getSubTagList());
            }
            accept(list);
        }, BaseUtil::toast);

//        setRoHttp((offset1, refresh) -> {
//
//            return getGridInflate();
//        });
    }

//    private Observable<List<GridInflate>> getGridInflate() {
//
//        return Observable.zip(categoriy, recommandList, (cate, entity) -> {
//            List<GridInflate> list = new ArrayList<>();
//            if (cate.getCode() == 0 && cate.getData() != null &&  !cate.getData().isEmpty()) {
//                list.addAll(cate.getData().subList(0,4));
//            }
//            if(entity.getCode()==0 &&entity.getData()!=null &&!entity.getData().isEmpty()){
//                for (RecommanData data:entity.getData()) {
//                    list.add(data);
//                    list.addAll(data.getList());
//                }
//
//            }
//            return list;
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
