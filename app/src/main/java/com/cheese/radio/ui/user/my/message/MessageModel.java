package com.cheese.radio.ui.user.my.message;

import android.os.Bundle;
import android.view.View;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMessageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/31.
 */
@ModelView(R.layout.activity_message)
public class MessageModel extends ViewHttpModel<MessageActivity,ActivityMessageBinding,MessagesData> {

    @Inject MessageModel(){}

    @Override
    public void accept(MessagesData messagesData) throws Exception {

    }
    private MessagesData messagesData;
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, MessageActivity activity) {
        super.attachView(savedInstanceState, activity);
        api.getMessages(new MessagesParams("getMessages")).compose(new RestfulTransformer<>())
                .subscribe(messagesData -> {
                    this.messagesData=messagesData;

                });
    }
    public void onSystemClick(View view){
        //跳转子消息
        Bundle bundle=new Bundle();
        bundle.putInt(Constant.id,0);
        bundle.putParcelableArrayList(Constant.detailsEntity,messagesData.getSystem());
        ARouterUtil.navigation(ActivityComponent.Router.detail,bundle);
    }
}
