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
    View view1,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of);

        debris = findViewById(R.id.debris);
        rocky = findViewById(R.id.rocky);
        next=findViewById(R.id.submit_button);
        view1=findViewById(R.id.debtick);
        view2=findViewById(R.id.rocktick);

        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);

        debris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.INVISIBLE);
                str="DEBRIS";
                next.setText("PROCEED");
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.INVISIBLE);
                str="DON'T KNOW";
                next.setText("DON'T KNOW");
            }
        });

        rocky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.INVISIBLE);
                str="ROCKY";
                next.setText("PROCEED");
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.setVisibility(View.INVISIBLE);
                str="DON'T KNOW";
                next.setText("DON'T KNOW");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(type_of.this,movement.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
            }
        });
    }

    public static String getType()
    {
        return str;
    }
}
