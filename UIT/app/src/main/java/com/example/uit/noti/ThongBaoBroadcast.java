package com.example.uit.noti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.uit.Data;
import com.example.uit.R;

import java.util.Date;

public class ThongBaoBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "deadlinenotify")
                .setSmallIcon(R.drawable.ic_baseline_calendar_today_24)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("content"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);



        Data.idNotify++;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(m, builder.build());

    }
}
