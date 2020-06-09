package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class land extends AppCompatActivity {

    ImageView cultivated,barren;
    Button back,next;
    int a=0,b=0;
    static String str="DON'T KNOW";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        
        cultivated = findViewById(R.id.cultivated);
        barren = findViewById(R.id.barren);
        next=findViewById(R.id.submit_button);



        cultivated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barren.setImageResource(R.drawable.ic_barren_land);
                b = 0;
                if(a%2==0)
                {
                    cultivated.setImageResource(R.drawable.ic_cultivated_clicked);
                    a++;
                    str="CULTIVATED";
                    next.setText("SUBMIT");
                }
                else
                {
                    cultivated.setImageResource(R.drawable.ic_cultivated);
                    a =0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        barren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cultivated.setImageResource(R.drawable.ic_cultivated);
                a=0;
                if(b%2==0)
                {
                    barren.setImageResource(R.drawable.ic_barren_land_clicked);
                    b++;
                    str="BARREN";
                    next.setText("SUBMIT");
                }
                else
                {
                    barren.setImageResource(R.drawable.ic_barren_land);
                    b = 0;
                    str="DON'T KNOW";
                    next.setText("DON'T  KNOW");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(land.this,Parameters.class));
            }
        });
    }

    public static String getLand()
    {
        return str;
    }
}
