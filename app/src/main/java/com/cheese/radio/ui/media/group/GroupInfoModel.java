package com.cheese.radio.ui.media.group;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.binding.model.adapter.ILayoutAdapter;
import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityGroupInfoBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.entity.AnchorEntity;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.activity_group_info)
public class GroupInfoModel extends PagerModel<GroupInfoActivity,ActivityGroupInfoBinding,AnchorEntity> {

    @Inject GroupInfoModel(@ActivityFragmentManager FragmentManager manager) {super(new FragmentAdapter<>(manager));}
    @Inject
    RadioApi api;
    private GroupInfoParams params=new GroupInfoParams("groupInfo");
    private static final Integer groupInfoId = new Integer(0);
    @Override
    public void attachView(Bundle savedInstanceState, GroupInfoActivity activity) {
        super.attachView(savedInstanceState, activity);
        Integer groupInfoId = getT().getIntent().getIntExtra(Constant.groupInfoId, -1);
//        api.getGroupInfo(params)

    }
}
