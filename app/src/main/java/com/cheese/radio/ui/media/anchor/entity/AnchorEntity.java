package com.cheese.radio.ui.media.anchor.entity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.binding.model.model.ViewParse;
import com.binding.model.model.inter.Item;
import com.cheese.radio.base.cycle.BaseFragment;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.AnchorData;
import com.cheese.radio.ui.media.anchor.entity.description.DescriptionFragment;
import com.cheese.radio.ui.media.group.fragment.story.PlayListFragment;
import com.cheese.radio.ui.media.play.PlayEntity;

import java.util.ArrayList;

/**
 * Created by 29283 on 2018/3/16.
 */

public class AnchorEntity extends ViewParse implements Item<BaseFragment> {

    private BaseFragment fragment;
    private int authorId;
    private AnchorData anchorData;

    public AnchorEntity(AnchorData anchorData) {
        this.anchorData = anchorData;
        this.authorId=anchorData.getAuthorId();
    }

    public AnchorEntity() {
    }
    ArrayList<PlayEntity> list=new ArrayList<>();
    @Override
    public BaseFragment getItem(int position, ViewGroup container) {
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new DescriptionFragment();break;
                case 1:
                    fragment = new PlayListFragment();break;
            }

            Bundle bundle = new Bundle();
//            bundle.putInt(Constant.authorId, authorId);
            list.addAll(anchorData.getSingle().getList());
            list.addAll(anchorData.getGroup().getList());
            bundle.putParcelableArrayList(Constant.anchorSingleItem,list);
            bundle.putString(Constant.description,anchorData.getDescription());
//            bundle.putInt(Constant.position, position);
            fragment.setArguments(bundle);
        }
        return fragment;
    }


}
