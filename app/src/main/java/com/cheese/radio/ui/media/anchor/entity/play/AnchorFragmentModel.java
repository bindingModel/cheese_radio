package com.cheese.radio.ui.media.anchor.entity.play;

import android.os.Bundle;

import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentAnchorBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.AnchorParams;
import com.cheese.radio.ui.media.play.PlayEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/16.
 */
@ModelView(R.layout.fragment_anchor)
public class AnchorFragmentModel extends RecyclerModel<AnchorFragment, FragmentAnchorBinding, PlayEntity> {
    @Inject
    AnchorFragmentModel() {
        super(new RecyclerAdapter<>());
    }
    private AnchorParams params;
    @Override
    public void attachView(Bundle savedInstanceState, AnchorFragment anchorFragment) {
        super.attachView(savedInstanceState, anchorFragment);
        Bundle bundle = anchorFragment.getArguments();
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
