package com.cheese.radio.ui.media.anchor.entity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.Constant;

import java.util.List;

/**
 * Created by 29283 on 2018/3/16.
 */

public class AnchorEntity extends ViewParse implements Item<BaseFragment> {

    private AnchorFragment fragment;
    private int authorId;

    public AnchorEntity(int authorId) {
        this.authorId = authorId;
    }

    public AnchorEntity() {
    }

    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if(fragment ==null){
            fragment = new AnchorFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId,authorId);
        bundle.putInt(Constant.position,position);
        fragment.setArguments(bundle);
        return fragment;
    }


}
