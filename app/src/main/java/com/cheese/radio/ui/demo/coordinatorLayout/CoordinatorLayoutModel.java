package com.cheese.radio.ui.demo.coordinatorLayout;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;

import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityDemoBinding;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.startup.check.CheckUpdateModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.ui.Constant.ACTION_BUTTON;
import static com.cheese.radio.ui.Constant.BUTTON_NEXT_ID;
import static com.cheese.radio.ui.Constant.BUTTON_PALY_ID;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;


/**
 * Created by 29283 on 2018/4/20.
 */
@ModelView(R.layout.activity_demo)
public class CoordinatorLayoutModel extends PagerModel<CoordinatorLayoutActivity, ActivityDemoBinding, CoordinatorLayoutEntity> {
    @Inject
    CoordinatorLayoutModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    private AudioServiceUtil util;
    private NotificationManager mNotificationManager;
    private final List<CoordinatorLayoutEntity> fragmentList = new ArrayList<>();
    @Inject
    CheckUpdateModel popupUpdate;

    @Override
    public void attachView(Bundle savedInstanceState, CoordinatorLayoutActivity coordinatorLayoutActivity) {
        super.attachView(savedInstanceState, coordinatorLayoutActivity);
        util = AudioServiceUtil.getInstance();
        mNotificationManager = (NotificationManager) getT().getSystemService(Context.NOTIFICATION_SERVICE);
//        sendNotification();
        initPopup(savedInstanceState);
    }

    public void getFragment() {
        if (fragmentList.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                fragmentList.add(new CoordinatorLayoutEntity());
            }
        }
    }

    public void showButtonNotify() {
        int NOTIFICATION_ID = 234;
        String CHANNEL_ID = "my_channel_01";
        NotificationManager notificationManager = (NotificationManager) getT().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getT(), CHANNEL_ID);
        RemoteViews mRemoteViews = new RemoteViews(getT().getPackageName(), R.layout.notify_music);
        mRemoteViews.setImageViewResource(R.id.music_icon, R.mipmap.app_logo);
        mRemoteViews.setTextViewText(R.id.music_title, "七里香");
        mRemoteViews.setTextViewText(R.id.music_subtitle, "周杰伦");

//        //点击的事件处理
        Intent buttonIntent = new Intent(ACTION_BUTTON);
        /* 上一首按钮 */

        //这里加了广播，所及INTENT的必须用getBroadcast方法

        /* 播放/暂停  按钮 */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_paly = PendingIntent.getBroadcast(getT(), 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_pause, intent_paly);
        /* 下一首 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(getT(), 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_next, intent_next);

        mBuilder.setContent(mRemoteViews)
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("正在播放")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(123, notify);
    }

    public static final String NOTIFICATION_CHANNEL_ID = "4655";

    public void onClick(View view) {
//        showButtonNotify();
        popupUpdate.show(window -> window.showAtLocation(getDataBinding().getRoot(), Gravity.CENTER, 0, 0));

//        int NOTIFICATION_ID = 234;
//        String CHANNEL_ID = "my_channel_01";
//
//        NotificationManager notificationManager = (NotificationManager) getT().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            CharSequence name = "my_channel";
//            String Description = "This is my channel";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            mChannel.setDescription(Description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mChannel.setShowBadge(false);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getT(), CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("123")
//                .setContentText(message);
//
//        Intent resultIntent = new Intent(getT(), CoordinatorLayoutActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getT());
//        stackBuilder.addParentStack(CoordinatorLayoutActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        builder.setContentIntent(resultPendingIntent);
//
//        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(getT(), 1, new Intent(), flags);
        return pendingIntent;
    }

    private void initPopup(Bundle savedInstanceState) {
        popupUpdate.attachContainer(getT(), (ViewGroup) getDataBinding().getRoot(), false, savedInstanceState);
        popupUpdate.getWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupUpdate.getWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupUpdate.getWindow().setAnimationStyle(R.style.contextMenuAnim);
        popupUpdate.show(window -> window.showAtLocation(getDataBinding().getRoot(), Gravity.CENTER, 0, 0));
    }

}

