package com.example.landslidereport01;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Review extends AppCompatActivity {

    EditText editText;
    Button button,back;
    DatabaseReference databaseReference;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        editText=findViewById(R.id.edit);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.text);
        back=findViewById(R.id.back);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        AlertDialog.Builder alert = new AlertDialog.Builder(Review.this);
        alert.setMessage("You can give reviews about problems that you faced or any other necessary feature you would like to be included.");
        alert.setPositiveButton("OK",null);
        alert.show();

        button.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Review.this, questions.class));
            }
        });

        final long unix = System.currentTimeMillis()/1000;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()!=0)
                {
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child("LAHRIS_REVIEW").child(String.valueOf(unix)).setValue(s.toString());
                            Toast.makeText(Review.this,"SUCCESSFUL",Toast.LENGTH_LONG).show();
                            button.setVisibility(View.INVISIBLE);
                            editText.setEnabled(false);
                            textView.setText("Thank you for your review !!");
                        }
                    });
                }

                else
                    button.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}