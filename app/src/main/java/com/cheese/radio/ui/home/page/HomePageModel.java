package com.cheese.radio.ui.home.page;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.GridInflate;
import com.cheese.radio.R;
import com.cheese.radio.databinding.FragmentHomePageBinding;
import com.cheese.radio.inject.api.IkeApi;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/3.
 */
@ModelView(R.layout.fragment_home_page)
public class HomePageModel extends RecyclerModel<HomePageFragment,FragmentHomePageBinding,GridInflate>{
    @Inject
    HomePageModel() {
    }

    @Inject
    IkeApi api;

    @Override
    public void attachView(Bundle savedInstanceState, HomePageFragment homePageFragment) {
        super.attachView(savedInstanceState, homePageFragment);


    }

    public void onSearchClick(View view){

    }
}
