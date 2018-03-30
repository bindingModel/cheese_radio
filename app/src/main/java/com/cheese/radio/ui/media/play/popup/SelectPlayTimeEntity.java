package com.cheese.radio.ui.media.play.popup;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.cheese.radio.R;

import static com.binding.model.adapter.AdapterType.select;
import static com.binding.model.adapter.IEventAdapter.CHECK;
import static com.binding.model.adapter.IEventAdapter.NO_POSITION;

/**
 * Created by 29283 on 2018/3/29.
 */
@ModelView(R.layout.holder_select_play_time)
public class SelectPlayTimeEntity extends ViewInflateRecycler {
    private int position;
    private Integer time;
    private String name;
    public final transient ObservableBoolean checked = new ObservableBoolean();

    public SelectPlayTimeEntity(String name, Integer time) {
        this.time = time;
        this.name = name;
    }

    @Override
    public ViewDataBinding attachView(Context context, ViewGroup co, boolean attachToParent, ViewDataBinding binding) {
        ViewDataBinding dataBinding = super.attachView(context, co, attachToParent, binding);
        if (getHolder_position() == 0 && getIEventAdapter() != null)
            getIEventAdapter().setEntity(NO_POSITION, this, select, null);
        return dataBinding;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void onSelectClick(View view) {
        getIEventAdapter().setEntity(position, this, select, view);
    }

    @Override
    public void check(boolean checked) {
        super.check(checked);
    }

    @Override
    public int getCheckType() {
        return CHECK;
    }
}
