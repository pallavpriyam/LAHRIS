package com.example.landslidereport01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class markerdetail extends AppCompatActivity {

    TextView user,lat,lon,type,move,trans,land;
    ImageView imageView;
    Button btn;

    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    final long MB = 1024*1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markerdetail);

        user=findViewById(R.id.username);
        lat=findViewById(R.id.latitude);
        lon=findViewById(R.id.longitude);
        type=findViewById(R.id.type);
        move=findViewById(R.id.movement);
        trans=findViewById(R.id.transport);
        land=findViewById(R.id.area);
        imageView = findViewById(R.id.image);


        String string = getIntent().getStringExtra("abc");

        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CASES").child(string);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.setText(dataSnapshot.child("name").getValue().toString());
                lat.setText(dataSnapshot.child("latitude").getValue().toString());
                lon.setText(dataSnapshot.child("longitude").getValue().toString());
                type.setText(dataSnapshot.child("type").getValue().toString());
                move.setText(dataSnapshot.child("movement").getValue().toString());
                trans.setText(dataSnapshot.child("transport").getValue().toString());
                land.setText(dataSnapshot.child("land").getValue().toString());

                storageReference= firebaseStorage.getReference().child(dataSnapshot.child("name").getValue().toString());

                storageReference.getBytes(MB).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
