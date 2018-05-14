package com.cheese.radio.ui.user.profile.popup;

import android.os.Bundle;
import android.view.View;

import com.binding.model.adapter.recycler.RecyclerSelectAdapter;
import com.binding.model.model.ModelView;
import com.binding.model.model.PopupRecyclerModel;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.databinding.PopupPictureBinding;
import com.cheese.radio.ui.user.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.binding.model.adapter.AdapterType.refresh;
import static com.binding.model.adapter.IEventAdapter.NO_POSITION;

/**
 * Created by 29283 on 2018/3/29.
 */
@ModelView(R.layout.popup_picture)
public class PopupPictureModel extends PopupRecyclerModel<BaseActivity,PopupPictureBinding,SelectPictureWayEntity>{
    @Inject PopupPictureModel(){super(new RecyclerSelectAdapter<>(1));}
    private List<SelectPictureWayEntity> entities = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, BaseActivity baseActivity) {
        super.attachView(savedInstanceState, baseActivity);
        entities.add(new SelectPictureWayEntity("拍照"));
        entities.add(new SelectPictureWayEntity("相册"));

        getAdapter().setList(NO_POSITION,entities,refresh);
    }

}
