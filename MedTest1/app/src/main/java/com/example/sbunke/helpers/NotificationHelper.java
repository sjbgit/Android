package com.example.sbunke.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.sbunke.activities.LoginActivity;
import com.example.sbunke.activities.PatientCheckInActivity;
import com.example.sbunke.activities.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sbunke on 10/17/2014.
 */
public class NotificationHelper {

    public static final int NOTE_BASIC = 100;
    public static final int NOTE_BIGTEXT = 200;
    public static final int NOTE_PICTURE = 300;
    public static final int NOTE_INBOX = 400;
    private NotificationManager manager;


    public static void showNotification(Context context) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildNotification(NOTE_BASIC, context);
        note.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        manager.notify(NOTE_BASIC, note);
        /*
        note = buildNotification(NOTE_BIGTEXT, context);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE, context);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX, context);
        manager.notify(NOTE_INBOX, note);
        */
    }

    public static Notification buildNotification(int type, Context context) {
        Intent launchIntent =
                new Intent(context, PatientCheckInActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                context);


        builder.setSmallIcon(R.drawable.kcmdrkonqi)
                .setTicker("Symptom Manager Check-In Reminder")
                .setWhen(System.currentTimeMillis())
                //.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("Time to check-in " + SimpleDateFormat.getTimeInstance().format(Calendar.getInstance().getTime()))
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
                pictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.kcmdrkonqi));

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


    public static void showPhysicianCheckInAlertNotification(Context context) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildPhysicianCheckInAlertNotification(NOTE_BASIC, context);
        note.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        manager.notify(NOTE_BASIC, note);
        /*
        note = buildNotification(NOTE_BIGTEXT, context);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE, context);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX, context);
        manager.notify(NOTE_INBOX, note);
        */
    }

    public static Notification buildPhysicianCheckInAlertNotification(int type, Context context) {


        Intent launchIntent =
                new Intent(context, LoginActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0, launchIntent, 0);



        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                context);


        builder.setSmallIcon(R.drawable.kcmdrkonqi)
                .setTicker("Patient Check-In Alert")
                .setWhen(System.currentTimeMillis())
                        //.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("CHECK IN ALERT")
                .setContentText("A patient has checked in exceeding an alert")
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
                pictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.kcmdrkonqi));

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































/*
    public static void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, PatientCheckInActivity.class), 0);

        Notification.Builder mBuilder =
                new Notification.Builder(context)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(0)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }

*/
    /*
    public NotificationHelper(NotificationManager manager) {
         this.manager = manager;
    }
*/
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
/*
    public Notification buildNotification(int type, PendingIntent contentIntent, Context ctx) {
  */
        /*
        Intent launchIntent =
                new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                NotificationActivity.this);

        */
/*
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
/*
    public static void testNotify() {
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildNotification(NOTE_BASIC);
        manager.notify(NOTE_BASIC, note);
        note = buildNotification(NOTE_BIGTEXT);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX);
        manager.notify(NOTE_INBOX, note);
    }

    public static Notification buildNotification(int type) {
        Intent launchIntent =
                new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                NotificationActivity.this);


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
                pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

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
*/
}
