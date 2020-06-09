package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class movement extends AppCompatActivity {

    ImageView topple,fall;
    Button back,next;
    int a=0,b=0;
    static String str="DON'T KNOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);


        topple = findViewById(R.id.topple);
        fall = findViewById(R.id.fall);
        next=findViewById(R.id.submit_button);

        topple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fall.setImageResource(R.drawable.ic_fall);
                b = 0;
                if(a%2==0)
                {
                    topple.setImageResource(R.drawable.ic_topple_clicked);
                    a++;
                    str="TOPPLE";
                    next.setText("SUBMIT");
                }
                else
                {
                    topple.setImageResource(R.drawable.ic_topple);
                    a =0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topple.setImageResource(R.drawable.ic_topple);
                a=0;
                if(b%2==0)
                {
                    fall.setImageResource(R.drawable.ic_fall_clicked);
                    b++;
                    str="FALL";
                    next.setText("SUBMIT");
                }
                else
                {
                    fall.setImageResource(R.drawable.ic_fall);
                    b = 0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(movement.this,transport.class));
            }
        });


    }

    public static String getMove()
    {
        return str;
    }
}
