package com.example.a533.testprojectcours5antoine.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.a533.testprojectcours5antoine.MainActivity;
import com.example.a533.testprojectcours5antoine.R;
import com.example.a533.testprojectcours5antoine.notification.model.ImportantMessageModel;
import com.example.a533.testprojectcours5antoine.notification.model.MessageModel;

public class NotificationCreator {

public static Notification createNotificationForMessage(Context context, MessageModel messageModel){
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"42")
            .setSmallIcon(R.drawable.lapin)
            .setContentTitle(messageModel.getSender())
            .setContentText(messageModel.getMessage())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    return builder.build();
}

public static Notification createNotificationForImportantMessage(Context context, ImportantMessageModel messageModel){
    Intent snoozeIntent = new Intent(context,MainActivity.class);

    PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "43")
            .setSmallIcon(R.drawable.lapin)
            .setContentTitle(messageModel.getSender())
            .setContentText(messageModel.getMessage())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.lapin,"Marqué comme lue", snoozePendingIntent);
    return builder.build();
}
}
