package com.example.testnotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buton = findViewById(R.id.boton);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String title = "Title";
        String description = "Description";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel chanel = new NotificationChannel(CHANNEL_ID, title, importance);
        chanel.setDescription(description);
        chanel.enableLights(true);
        chanel.setLightColor(Color.RED);
        chanel.enableVibration(true);
        chanel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        mNotificationManager.createNotificationChannel(chanel);

        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);


        final Notification notification = new Notification.Builder(MainActivity.this)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.ic_baseline_shopping_cart_24)
                .setChannelId(CHANNEL_ID)
                .build();

        //Pending to implement
        final Intent intent = new Intent(this, AlertDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


/*
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_shopping_cart_24)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

         final NotificationManagerCompat notifiction = NotificationManagerCompat.from(this);
*/

        final NotificationManager finalMNotificationManager = mNotificationManager;
        buton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //notifiction.notify(100,mBuilder.build());
                finalMNotificationManager.notify(1, notification);

            }
        });

    }
}