package com.cheese.radio.ui.user.favority.my;

import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityMyFavorityBinding;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/25.
 */
@ModelView(R.layout.activity_my_favority)
public class MyFavorityModel extends RecyclerModel<MyFavorityActivity,ActivityMyFavorityBinding,GridInflate>{
    @Inject MyFavorityModel(){}

    @Override
    public void attachView(Bundle savedInstanceState, MyFavorityActivity activity) {
        super.attachView(savedInstanceState, activity);
    }
}
