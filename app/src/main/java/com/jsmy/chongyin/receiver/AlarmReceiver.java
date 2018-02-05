package com.jsmy.chongyin.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.activity.NoteEditActivity;
import com.jsmy.chongyin.utils.VibrateUtils;

/**
 * Created by Administrator on 2017/11/29.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == NoteEditActivity.INTENT_ALARM_LOG) {
            showNotification(context, Integer.parseInt(intent.getStringExtra("noteId")), "重要提醒", intent.getStringExtra("nr"), intent.getStringExtra("noteId"));
            VibrateUtils.vibrate(context, new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}, -1);
        }
    }

    private void showNotification(Context context, int id, String title, String text, String noteId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.chong_icon);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        builder.setOnlyAlertOnce(true);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
        Intent resultIntent = new Intent(context, NoteEditActivity.class);
        resultIntent.putExtra("noteId", noteId);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, id,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
