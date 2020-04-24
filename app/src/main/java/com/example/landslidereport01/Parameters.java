package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class Parameters extends AppCompatActivity {

    TextView type,move,trans,area;
    Button next,back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        type=findViewById(R.id.type);
        move=findViewById(R.id.movement);
        trans=findViewById(R.id.transport);
        area=findViewById(R.id.land);
        back=findViewById(R.id.back);
        next=findViewById(R.id.submit_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parameters.this,land.class));
            }
        });


        type.setText(type_of.getType());
        move.setText(movement.getMove());
        trans.setText(transport.getTrans());
        area.setText(land.getLand());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parameters.this,Image.class));
            }
        });

    }
}
