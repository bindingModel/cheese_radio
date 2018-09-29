package com.cheese.radio.ui.media.group.fragment.story;

import android.os.Bundle;
import android.os.Parcelable;

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

import io.reactivex.Observable;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(R.layout.fragment_play_list)
public class PlayListFragmentModel extends RecyclerModel<PlayListFragment, FragmentPlayListBinding, PlayEntity> {

    @Inject
    PlayListFragmentModel() {
    }


    @Override
    public void attachView(Bundle savedInstanceState, PlayListFragment fragment) {
        super.attachView(savedInstanceState, fragment);
        Bundle bundle = fragment.getArguments();
        if (bundle != null) {
            List<PlayEntity> list = bundle.getParcelableArrayList(Constant.anchorSingleItem);
            if (list != null)
                try {
                    accept(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            setRecycleViewEvent();
        }
    }

    public void setRecycleViewEvent() {
        addEventAdapter((position, o, type, view) -> {
            ArrayList<PlayEntity> entities = new ArrayList<>();
            Observable.fromIterable(getAdapter().getList()).filter(playEntity -> o.getLocation().equals(playEntity.getLocation()))
                    .toList().toObservable()
                    .subscribe((playEntities -> {
                        int indexOf = playEntities.indexOf(o);
                        if (indexOf == -1) {return;}
                        Bundle bundle = new Bundle();
                        bundle.putInt(Constant.indexOf, indexOf);
                        bundle.putParcelableArrayList(Constant.playList, (ArrayList<PlayEntity>) playEntities);
                        ARouterUtil.LocationNavigation(o.getLocation(), bundle);
                    }));
            return true;
        });
    }
}
