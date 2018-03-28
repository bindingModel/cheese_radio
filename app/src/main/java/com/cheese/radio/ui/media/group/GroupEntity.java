package com.cheese.radio.ui.media.group;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.group.fragment.GroupData;
import com.cheese.radio.ui.media.group.fragment.introduce.GroupIntroduceFragment;
import com.cheese.radio.ui.media.group.fragment.story.GroupStoryFragment;

import java.io.Serializable;

/**
 * Created by 29283 on 2018/3/21.
 */

public class GroupEntity extends ViewParse implements Item<BaseFragment> {

    private BaseFragment fragment;
    private int authorId;
    private GroupData groupData;
    public GroupEntity(GroupData groupData) {
        this.groupData = groupData;
    }

    public GroupEntity() {
    }

    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if(fragment ==null){
            switch (position){
                case 0: fragment = new GroupIntroduceFragment();break;
                case 1: fragment = new GroupStoryFragment();break;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constant.description,groupData.getDescription());
//        bundle.putInt(Constant.position,position);
        bundle.putParcelableArrayList(Constant.anchorSingleItem,groupData.getContentList());
        fragment.setArguments(bundle);
        return fragment;
    }


}
