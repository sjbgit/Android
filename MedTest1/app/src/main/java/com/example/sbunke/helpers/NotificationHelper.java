package com.example.sbunke.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.sbunke.activities.R;

/**
 * Created by sbunke on 10/17/2014.
 */
public class NotificationHelper {

    public static final int NOTE_BASIC = 100;
    public static final int NOTE_BIGTEXT = 200;
    public static final int NOTE_PICTURE = 300;
    public static final int NOTE_INBOX = 400;
    private NotificationManager manager;

    public NotificationHelper(NotificationManager manager) {
         this.manager = manager;
    }

    /*
    //Post 4 unique notifications
    Notification note = buildNotification(NOTE_BASIC);
    manager.notify(NOTE_BASIC, note);
    note = buildNotification(NOTE_BIGTEXT);
    manager.notify(NOTE_BIGTEXT, note);
    note = buildNotification(NOTE_PICTURE);
    manager.notify(NOTE_PICTURE, note);
    note = buildNotification(NOTE_INBOX);
    manager.notify(NOTE_INBOX, note);
    */

    public Notification buildNotification(int type, PendingIntent contentIntent, Context ctx) {
        /*
        Intent launchIntent =
                new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                NotificationActivity.this);

        */

        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Something Happened")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("We're Finished!")
                .setContentText("Click Here!")
                .setContentIntent(contentIntent);

        switch (type) {
            case NOTE_BASIC:
                //Return the simple notification
                return builder.build();
            case NOTE_BIGTEXT:
                //Include two actions
                builder.addAction(android.R.drawable.ic_menu_call,
                        "Call", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history,
                        "History", contentIntent);
                //Use the BigTextStyle when expanded
                Notification.BigTextStyle textStyle =
                        new Notification.BigTextStyle(builder);
                textStyle.bigText(
                        "Here is some additional text to be displayed when the notification is "
                                +"in expanded mode.  I can fit so much more content into this giant view!");

                return textStyle.build();
            case NOTE_PICTURE:
                //Add one additional action
                builder.addAction(android.R.drawable.ic_menu_compass,
                        "View Location", contentIntent);
                //Use the BigPictureStyle when expanded
                Notification.BigPictureStyle pictureStyle =
                        new Notification.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.ic_launcher));

                return pictureStyle.build();
            case NOTE_INBOX:
                //Use the InboxStyle when expanded
                Notification.InboxStyle inboxStyle =
                        new Notification.InboxStyle(builder);
                inboxStyle.setSummaryText("4 New Tasks");
                inboxStyle.addLine("Make Dinner");
                inboxStyle.addLine("Call Mom");
                inboxStyle.addLine("Call Wife First");
                inboxStyle.addLine("Pick up Kids");

                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("Unknown Type");
        }

    }
}
