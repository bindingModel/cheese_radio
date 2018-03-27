package com.cheese.radio.ui.media.anchor.entity.play;

import android.os.Bundle;

import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.FragmentAnchorBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.AnchorParams;
import com.cheese.radio.ui.media.anchor.entity.play.item.AnchorSingleItem;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/16.
 */
@ModelView(R.layout.fragment_anchor)
public class AnchorFragmentModel extends RecyclerModel<AnchorFragment, FragmentAnchorBinding, AnchorSingleItem> {
    @Inject
    AnchorFragmentModel() {
        super(new RecyclerAdapter<>());
    }
    private AnchorParams params;
    @Inject RadioApi api;
    @Override
    public void attachView(Bundle savedInstanceState, AnchorFragment anchorFragment) {
        super.attachView(savedInstanceState, anchorFragment);
        Bundle bundle = anchorFragment.getArguments();
        Integer authorId = bundle.getInt(Constant.authorId, -1);
        if(authorId !=-1)
        params=new AnchorParams("info", authorId);
         setRcHttp((offset1, refresh) ->  api.getAuthor(params).compose(new RestfulTransformer<>()).map(
                    anchorData -> {return anchorData.getSingle().getList();}
            ));
    }
}
