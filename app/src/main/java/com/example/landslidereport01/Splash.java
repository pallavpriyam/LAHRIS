package com.example.landslidereport01;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class Splash extends AppCompatActivity {

    SharedPreferences onBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel= new NotificationChannel("mn","mn", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications")
                .addOnCompleteListener(task -> {
                    String msg = "Subscribed";
                    if (!task.isSuccessful()) {
                        msg = "Not Subscribed";
                    }
                    Toast.makeText(Splash.this, msg, Toast.LENGTH_SHORT).show();
                });

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                onBoard = getSharedPreferences("onBoard",MODE_PRIVATE);
                boolean isFirstTime = onBoard.getBoolean("firstTime",true);

                if(isFirstTime)
                {
                 SharedPreferences.Editor editor=onBoard.edit();
                 editor.putBoolean("firstTime",false);
                 editor.commit();

                    Intent mainIntent = new Intent(Splash.this,WelcomeActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();

                }
                else {
                    Intent mainIntent = new Intent(Splash.this, questions.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }
        }, 2000);

    }
}
