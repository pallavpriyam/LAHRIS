package com.example.landslidereport01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    SharedPreferences onBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
