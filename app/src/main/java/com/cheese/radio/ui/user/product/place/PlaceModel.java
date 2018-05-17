package com.cheese.radio.ui.user.product.place;

import android.os.Bundle;
import android.view.View;

import com.binding.model.cycle.Container;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPlaceBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.enroll.params.ClassPlaceParams;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/4/20.
 */
@ModelView(R.layout.activity_place)
public class PlaceModel extends RecyclerModel<PlaceActivity, ActivityPlaceBinding, ClassPlaceEntity> {
    private static int placeId;

    @Inject
    PlaceModel() {
    }

    @Inject
    RadioApi api;
    private final List<ClassPlaceEntity> list = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, PlaceActivity placeActivity) {
        super.attachView(savedInstanceState, placeActivity);
        getDataBinding().layoutRecycler.setVm(this);
        setPageFlag(false);
        placeId=getT().getIntent().getIntExtra(Constant.placeId,0);
        setRcHttp((offset1, refresh) -> {
            if (refresh) list.clear();
            return api.classPlace(
                    new ClassPlaceParams("classPlace"))
                    .compose(new RestfulTransformer<>())
                    .map(classPlaceEntities -> {
                        for (ClassPlaceEntity entity : classPlaceEntities) {
                            if (entity.getId() == placeId) entity.check.set(true);
                        }
                        list.addAll(classPlaceEntities);
                        return list;
                    });
        });
        addEventAdapter((position, classPlaceEntity, type, view) -> {
            for (ClassPlaceEntity entity :
                    getAdapter().getList()) {
                entity.check.set(false);
            }
            classPlaceEntity.check.set(true);
            getDataBinding().save.setVisibility(View.VISIBLE);
            getT().finish();
            return false;
        });
    }

    public void onFinishClick(View view) {
        finish();
    }
}
