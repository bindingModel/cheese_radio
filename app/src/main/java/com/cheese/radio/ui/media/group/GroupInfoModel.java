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
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityGroupInfoBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.AnchorData;
import com.cheese.radio.ui.media.anchor.entity.AnchorEntity;
import com.cheese.radio.ui.media.group.fragment.GroupData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.activity_group_info)
public class GroupInfoModel extends PagerModel<GroupInfoActivity, ActivityGroupInfoBinding, GroupEntity> {

    @Inject
    GroupInfoModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    @Inject
    RadioApi api;
    private GroupInfoParams params = new GroupInfoParams("groupInfo");
    private static final Integer groupInfoId = new Integer(0);
    private GroupData groupData;
    private final List<GroupEntity> list = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, GroupInfoActivity activity) {
        super.attachView(savedInstanceState, activity);
        Integer groupInfoId = getT().getIntent().getIntExtra(Constant.id, -1);
        params.setId(groupInfoId);
        addDisposable(api.getGroupInfo(params).compose(new RestfulTransformer<>()).subscribe(
                groupData -> {
                    getDataBinding().setEntity(groupData);
                    setFragment(groupData);
                    getDataBinding().storyTitle.setText(String.format("作品（%1s)", groupData.getContentList().size()));
                }
        ));

    }

    public void setFragment(GroupData groupData) {
        list.add(new GroupEntity(groupData));
        list.add(new GroupEntity(groupData));
        try {
            accept(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
