package com.example.landslidereport01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    EditText abc;
    static String string;
    Button subbtn,btnch,btnup,back;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subbtn=findViewById(R.id.submit_button);
        abc=findViewById(R.id.editName);
        img=findViewById(R.id.image);
        btnch=findViewById(R.id.btnchoose);
        btnup=findViewById(R.id.btnupload);

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=abc.getText().toString();
                startActivity(new Intent(MainActivity.this,Location.class));
            }
        });

    }

    public static String getName(){

        return string;

    }

}
