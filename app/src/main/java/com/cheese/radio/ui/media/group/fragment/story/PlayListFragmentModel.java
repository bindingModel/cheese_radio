package com.cheese.radio.ui.media.group.fragment.story;

import android.os.Bundle;

import com.binding.model.adapter.IEventAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.FragmentPlayListBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.play.PlayEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_play_list)
public class PlayListFragmentModel extends RecyclerModel<PlayListFragment, FragmentPlayListBinding, PlayEntity> {
    private List<PlayEntity> list;

    @Inject
    PlayListFragmentModel() {
    }

    @Override
    public void attachView(Bundle savedInstanceState, PlayListFragment fragment) {
        super.attachView(savedInstanceState, fragment);
        Bundle bundle = fragment.getArguments();
        if (bundle != null && list == null) {
            list = bundle.getParcelableArrayList(Constant.anchorSingleItem);
            bundle.clear();
        }
        if (list != null)
            try {
                getAdapter().addListAdapter(IEventAdapter.NO_POSITION, list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        setRecycleViewEvent();
    }

    public void setRecycleViewEvent() {
        addEventAdapter((position, o, type, view) -> {
            int indexOf = getAdapter().getList().indexOf(o);
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.indexOf, indexOf);
            bundle.putInt(Constant.id, o.getId());
//            getT().getActivity().startActivity(intent);
            bundle.putParcelableArrayList(Constant.playList, (ArrayList<PlayEntity>) getAdapter().getList());
            ARouterUtil.LocationNavigation(o.getLocation(), bundle);
            return true;
        });
    }
}
