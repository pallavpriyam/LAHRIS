package com.example.landslidereport01;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.TextViewCompat;

public class questions extends AppCompatActivity {

    CardView c1,c2,c3,c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        TextViewCompat.setAutoSizeTextTypeWithDefaults((TextView) findViewById(R.id.text), TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        c1=findViewById(R.id.landslides);
        c2=findViewById(R.id.add);
        c3=findViewById(R.id.web);
        c4=findViewById(R.id.team);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(questions.this,MapsActivityHome.class));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(questions.this, MainActivity.class));
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://1eu9mz1bgqv71kbftc2jqony-wpengine.netdna-ssl.com/wp-content/uploads/2016/06/Ready-Banner.jpg");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ActivityCompat.requestPermissions(questions.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(questions.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

    }
}
