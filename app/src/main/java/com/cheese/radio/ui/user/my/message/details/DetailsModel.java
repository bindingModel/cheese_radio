package com.cheese.radio.ui.user.my.message.details;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityDetailsBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.my.message.ReadMessagesParams;
import com.cheese.radio.ui.user.my.message.entity.DetailsEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/31.
 */
@ModelView(value = R.layout.activity_details,event = R.id.DetailsModel)
public class DetailsModel extends RecyclerModel<DetailsActivity,ActivityDetailsBinding,DetailsEntity>{
    @Inject DetailsModel(){}

    @Inject RadioApi api;

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


    @Override
    public int onEvent(View view, Event event, Object... args) {

        //阅读消息；
        DetailsEntity entity= event instanceof DetailsEntity ? (DetailsEntity) event : null;
        if(entity!=null){
            ReadMessagesParams params =new ReadMessagesParams("readMessages");
            params.setId(entity.getId());
            addDisposable(api.readMessages(params).compose(new RestfulTransformer<>()).subscribe(s -> {
                BaseUtil.toast("确认消息");
            }, BaseUtil::toast));
//            entity.aBoolean.set(false);
//            entity.msg.set(entity.getTitle());
        }


        return super.onEvent(view, event, args);
    }
}
