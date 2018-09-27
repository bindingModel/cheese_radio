package com.cheese.radio.ui.media.group.fragment.story;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentGroupStoryBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.play.PlayEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_group_story)
public class GroupStoryFragmentModel extends RecyclerModel<GroupStoryFragment, FragmentGroupStoryBinding, PlayEntity> {

    @Inject
    GroupStoryFragmentModel() {
    }


    @Override
    public void attachView(Bundle savedInstanceState, GroupStoryFragment groupStoryFragment) {
        super.attachView(savedInstanceState, groupStoryFragment);
        Bundle bundle = groupStoryFragment.getArguments();
        if (bundle != null ) {
            List<PlayEntity> list = bundle.getParcelableArrayList(Constant.anchorSingleItem);
            if(list!=null)
            try {
                accept(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
