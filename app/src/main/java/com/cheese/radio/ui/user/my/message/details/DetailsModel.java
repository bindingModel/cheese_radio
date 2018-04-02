package com.cheese.radio.ui.user.my.message.details;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityDetailsBinding;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.my.message.entity.DetailsEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/31.
 */
@ModelView(R.layout.activity_details)
public class DetailsModel extends RecyclerModel<DetailsActivity,ActivityDetailsBinding,DetailsEntity>{
    @Inject DetailsModel(){}

    private final List<DetailsEntity> list=new ArrayList<>();
    public ObservableField<String> title=new ObservableField<>("系统通知");
    @Override
    public void attachView(Bundle savedInstanceState, DetailsActivity activity) {
        super.attachView(savedInstanceState, activity);
        setEnable(false);
        setPageFlag(false);
        getDataBinding().layoutRecycler.setVm(this);
        getTitle(getT().getIntent().getIntExtra(Constant.id,0));
        list.addAll(getT().getIntent().getParcelableArrayListExtra(Constant.detailsEntity));
        try {
            accept(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getTitle(int id){
        switch (id){
            case 0:title.set("系统通知");break;
            case 1:title.set("会员通知");break;
            case 2:title.set("上课通知");break;
            case 3:title.set("预约通知");break;
        }
    }
}
