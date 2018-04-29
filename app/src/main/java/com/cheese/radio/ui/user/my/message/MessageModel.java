package com.cheese.radio.ui.user.my.message;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.model.inter.Event;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityMessageBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.calendar.CalendarEntity;
import com.cheese.radio.ui.user.my.message.entity.DetailsEntity;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/31.
 */
@ModelView(value = R.layout.activity_message,event = R.id.EnrollModel,model = true)
public class MessageModel extends ViewHttpModel<MessageActivity, ActivityMessageBinding, MessagesData> {

    @Inject
    MessageModel() {
    }

    @Override
    public void accept(MessagesData messagesData) throws Exception {
        this.messagesData = messagesData;
        initMsg();
    }

    public final ObservableField<ArrayList<CalendarEntity>> theDayClass = new ObservableField<>();

    private MessagesData messagesData;
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, MessageActivity activity) {
        super.attachView(savedInstanceState, activity);
        setRcHttp((offset1, refresh) -> api.getMessages(new MessagesParams("getMessages")).compose(new RestfulTransformer<>())
        );

    }

    public void onSystemClick(View view) {
        //跳转子消息
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.system_message: {
                bundle.putInt(Constant.id, 0);
                bundle.putParcelableArrayList(Constant.detailsEntity, messagesData.getSystem());
                break;
            }
            case R.id.vip_message: {
                bundle.putInt(Constant.id, 1);
                bundle.putParcelableArrayList(Constant.detailsEntity, messagesData.getUser());
                break;
            }
            case R.id.class_message: {
                bundle.putInt(Constant.id, 2);
                bundle.putParcelableArrayList(Constant.detailsEntity, messagesData.getClassX());
                break;
            }
            case R.id.book_message: {
                bundle.putInt(Constant.id, 3);
                bundle.putParcelableArrayList(Constant.detailsEntity, messagesData.getBook());
                break;
            }
        }
        ARouterUtil.navigation(ActivityComponent.Router.detail, bundle);
    }

    private void initMsg() {

        getDataBinding().sysText.setText(findNewMSG(messagesData.getSystem()));

        getDataBinding().vipText.setText(findNewMSG(messagesData.getUser()));

        getDataBinding().classText.setText(findNewMSG(messagesData.getClassX()));

        getDataBinding().bookText.setText(findNewMSG(messagesData.getBook()));

    }
    private String findNewMSG(ArrayList<DetailsEntity> list){
        String msg="暂无新消息";
        for (DetailsEntity entity: list ) {
           if(entity.isIsRead())continue;
           msg=entity.getContent();
        }
        return msg;
    }
    public void upDataMsg(){
        onHttp(1);
    }
}
