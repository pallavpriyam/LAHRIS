package com.example.landslidereport01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class progressbar extends AppCompatActivity {

    static progressbar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        pb=this;
    }

    public static progressbar endme()
    {
        return pb;
    }


}
