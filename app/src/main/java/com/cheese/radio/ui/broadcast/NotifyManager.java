package com.cheese.radio.ui.broadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.binding.model.App;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.cheese.radio.ui.Constant.ACTION_BUTTON;
import static com.cheese.radio.ui.Constant.BUTTON_CANCEL_ID;
import static com.cheese.radio.ui.Constant.BUTTON_NEXT_ID;
import static com.cheese.radio.ui.Constant.BUTTON_PALY_ID;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.ui.broadcast
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/27 8:45 PM
 * @change
 * @chang time
 * @class describe
 */
public class NotifyManager {
    private static NotificationManager notificationManager;
    private static NotificationChannel mChannel;
    private static int msgId=0x4396;
    public NotifyManager() {
        initNotificationManager();
    }
    public static NotificationManager getNotificationManager(){
        if(notificationManager==null)initNotificationManager();
        return notificationManager;
    }
    private static void initNotificationManager(){
        if(notificationManager==null)notificationManager = (NotificationManager) App.getCurrentActivity().getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "cheese_channel";
            String Description = "This is cheese channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            mChannel = new NotificationChannel(App.getCurrentActivity().getPackageName(), name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.WHITE);
            mChannel.enableVibration(false);
            mChannel.setVibrationPattern(null);//震动
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
    }
    public static void builderNotification(Bitmap image, String title, String subTitle){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getCurrentActivity(), App.getCurrentActivity().getPackageName());
        RemoteViews mRemoteViews = new RemoteViews(BuildConfig.APPLICATION_ID, R.layout.notify_music);

        try {
            mRemoteViews.setImageViewBitmap(R.id.music_icon, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRemoteViews.setTextViewText(R.id.music_title, title);
        mRemoteViews.setTextViewText(R.id.music_subtitle, subTitle);

//        //点击的事件处理
        Intent buttonIntent = new Intent(ACTION_BUTTON);

        //这里加了广播，所及INTENT的必须用getBroadcast方法
        /* 播放/暂停  按钮 */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_play = PendingIntent.getBroadcast(App.getCurrentActivity(), 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_pause, intent_play);
//        mRemoteViews.setOnClickPendingIntent(R.id.music_stop,intent_play);
        /* 下一首 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(App.getCurrentActivity(), 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_next, intent_next);
        /* 取消 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_CANCEL_ID);
        PendingIntent intent_cancel = PendingIntent.getBroadcast(App.getCurrentActivity(), 3, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_cancel, intent_cancel);

        mBuilder.setContent(mRemoteViews)
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("正在播放")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(getMsgId(), notify);

    }
    private static PendingIntent getDefalutIntent(int flags) {
        return PendingIntent.getActivity(App.getCurrentActivity(), 1, new Intent(), flags);
    }

    public static int getMsgId() {
        return msgId;
    }

    public static void setMsgId(int msgId) {
        NotifyManager.msgId = msgId;
    }
}
