package com.cheese.radio.ui.media.picture;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityPictureBinding;

@ModelView(R.layout.activity_picture)
public class PictureModel extends ViewHttpModel<PictureActivity,ActivityPictureBinding,Object>{
    @Override
    public void accept(Object o) throws Exception {

    }
    private ViewPager viewPager;
//    private
    @Override
    public void attachView(Bundle savedInstanceState, PictureActivity pictureActivity) {
        super.attachView(savedInstanceState, pictureActivity);
    }
}
