package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class type_of extends AppCompatActivity {

    ImageView debris,rocky;
    Button back,next;
    int a=0,b=0;
    static String str="DON'T KNOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of);

        debris = findViewById(R.id.debris);
        rocky = findViewById(R.id.rocky);
        back=findViewById(R.id.back);
        next=findViewById(R.id.submit_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(type_of.this,Location.class));
            }
        });

        debris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rocky.setImageResource(R.drawable.r);
                b = 0;
                if(a%2==0)
                {
                    debris.setImageResource(R.drawable.dc);
                    a++;
                    str="DEBRIS";
                    next.setText("SUBMIT");
                }
                else
                {
                    debris.setImageResource(R.drawable.d);
                    a =0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        rocky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debris.setImageResource(R.drawable.d);
                a=0;
                if(b%2==0)
                {
                    rocky.setImageResource(R.drawable.rc);
                    b++;
                    str="ROCKY";
                    next.setText("SUBMIT");
                }
                else
                {
                    rocky.setImageResource(R.drawable.r);
                    b = 0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(type_of.this,movement.class));
            }
        });
    }

    public static String getType()
    {
        return str;
    }
}
