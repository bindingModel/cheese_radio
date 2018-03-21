package com.cheese.radio.ui.media.group.fragment.introduce;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentGroupBriefIntroductionBinding;
import com.cheese.radio.ui.media.group.GroupInfoModel;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_group_brief_introduction)
public class GroupIntroduceModel extends ViewModel<GroupIntroduceFragment,FragmentGroupBriefIntroductionBinding>{
    @Inject GroupIntroduceModel(){}

    @Override
    public void attachView(Bundle savedInstanceState, GroupIntroduceFragment groupIntroduceFragment) {
        super.attachView(savedInstanceState, groupIntroduceFragment);
    }
}
