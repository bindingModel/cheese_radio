package com.cheese.radio.ui.media.group.fragment.story;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentGroupStoryBinding;
import com.cheese.radio.ui.media.anchor.entity.play.item.AnchorSingleItem;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_group_story)
public class GroupStoryFragmentModel extends RecyclerModel<GroupStoryFragment,FragmentGroupStoryBinding,AnchorSingleItem> {

    @Inject GroupStoryFragmentModel(){}
    @Override
    public void attachView(Bundle savedInstanceState, GroupStoryFragment groupStoryFragment) {
        super.attachView(savedInstanceState, groupStoryFragment);
    }
}
