package com.cheese.radio.ui.media.group;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.group.fragment.introduce.GroupIntroduceFragment;
import com.cheese.radio.ui.media.group.fragment.story.GroupStoryFragment;

/**
 * Created by 29283 on 2018/3/21.
 */

public class GroupEntity extends ViewParse implements Item<BaseFragment> {

    private BaseFragment fragment;
    private int authorId;

    public GroupEntity(int authorId) {
        this.authorId = authorId;
    }

    public GroupEntity() {
    }

    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if(fragment ==null){
            switch (position){
                case 0: fragment = new GroupIntroduceFragment();
                case 1: fragment = new GroupStoryFragment();
            }


        }
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId,authorId);
        bundle.putInt(Constant.position,position);
        fragment.setArguments(bundle);
        return fragment;
    }


}
