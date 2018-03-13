package com.cheese.radio.ui.home.mine;

import android.os.Bundle;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentHomeMineBinding;
import com.cheese.radio.ui.home.page.HomePageModel;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(R.layout.fragment_home_mine)
public class HomeMineModel extends ViewModel<HomeMineFragment,FragmentHomeMineBinding>{
    @Inject HomeMineModel(){}

    @Override
    public void attachView(Bundle savedInstanceState, HomeMineFragment homeMineFragment) {
        super.attachView(savedInstanceState, homeMineFragment);

    }
}
