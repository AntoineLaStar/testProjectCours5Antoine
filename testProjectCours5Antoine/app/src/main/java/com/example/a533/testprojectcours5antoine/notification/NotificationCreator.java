package com.example.a533.testprojectcours5antoine.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.a533.testprojectcours5antoine.R;
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

}
