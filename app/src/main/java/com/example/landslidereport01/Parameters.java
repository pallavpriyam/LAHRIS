package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Parameters extends AppCompatActivity {

    TextView type,move,trans,area,name,time;
    Button next,back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        type=findViewById(R.id.type);
        move=findViewById(R.id.movement);
        trans=findViewById(R.id.transport);
        area=findViewById(R.id.land);
        next=findViewById(R.id.submit_button);
        name=findViewById(R.id.name);
        time=findViewById(R.id.time);


        type.setText(type_of.getType());
        move.setText(movement.getMove());
        trans.setText(transport.getTrans());
        area.setText(land.getLand());
        name.setText(Location.getName());

        long unix = System.currentTimeMillis()/1000;

        Date date = new java.util.Date(unix*1000L);
// the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy       HH:mm:ss");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+5:30"));
        String formattedDate = sdf.format(date);
        time.setText(formattedDate);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parameters.this,Image.class));
            }
        });

    }
}
