package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class transport extends AppCompatActivity {

    ImageView railways,roadways;
    Button back,next;
    int a=0,b=0;
    static String str="DON'T KNOW";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        
        railways = findViewById(R.id.railways);
        roadways = findViewById(R.id.roadways);
        next=findViewById(R.id.submit_button);



        railways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roadways.setImageResource(R.drawable.ic_roadways);
                b = 0;
                if(a%2==0)
                {
                    railways.setImageResource(R.drawable.ic_railway_clicked);
                    a++;
                    str="RAILWAYS";
                    next.setText("SUBMIT");
                }
                else
                {
                    railways.setImageResource(R.drawable.ic_railway);
                    a =0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        roadways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                railways.setImageResource(R.drawable.ic_railway);
                a=0;
                if(b%2==0)
                {
                    roadways.setImageResource(R.drawable.ic_roadways_clicked);
                    b++;
                    str="ROADWAYS";
                    next.setText("SUBMIT");
                }
                else
                {
                    roadways.setImageResource(R.drawable.ic_roadways);
                    b = 0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(transport.this,land.class));
            }
        });

    }

    public static String getTrans()
    {
        return str;
    }

}
