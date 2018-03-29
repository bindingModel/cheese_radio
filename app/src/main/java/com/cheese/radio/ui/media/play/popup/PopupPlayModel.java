package com.cheese.radio.ui.media.play.popup;

import android.os.Bundle;
import android.view.View;

import com.binding.model.adapter.recycler.RecyclerSelectAdapter;
import com.binding.model.model.ModelView;
import com.binding.model.model.PopupRecyclerModel;
import com.cheese.radio.R;
import com.cheese.radio.base.cycle.BaseActivity;
import com.cheese.radio.databinding.PopupPlayBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.binding.model.adapter.AdapterType.refresh;
import static com.binding.model.adapter.IEventAdapter.NO_POSITION;

/**
 * Created by 29283 on 2018/3/29.
 */
@ModelView(R.layout.popup_play)
public class PopupPlayModel extends PopupRecyclerModel<BaseActivity,PopupPlayBinding,SelectPlayTimeEntity>{
    @Inject PopupPlayModel(){super(new RecyclerSelectAdapter<>(1));}
    private List<SelectPlayTimeEntity> entities = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, BaseActivity baseActivity) {
        super.attachView(savedInstanceState, baseActivity);
        entities.add(new SelectPlayTimeEntity("不开启","0"));
        entities.add(new SelectPlayTimeEntity("15分钟后停止播放","900"));
        entities.add(new SelectPlayTimeEntity("30分钟后停止播放","1800"));
        entities.add(new SelectPlayTimeEntity("45分钟后停止播放","2700"));
        entities.add(new SelectPlayTimeEntity("60分钟后停止播放","3600"));
        getAdapter().setList(NO_POSITION,entities,refresh);
    }

    public void onDismissClick(View view){
        getWindow().dismiss();
    }
}
