package com.example.landslidereport01;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Image extends AppCompatActivity {

    Button btnch,btnup,btnpro;
    ImageView imageView;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    byte[] biti;

    working wor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        btnch=findViewById(R.id.btnchoose);
        imageView=findViewById(R.id.imageview);
        btnup=findViewById(R.id.btnupload);
        btnpro=findViewById(R.id.proceed);
        btnpro.setEnabled(false);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        wor=new working();

        wor.setType(type_of.getType());
        wor.setMovement(movement.getMove());
        wor.setTransport(transport.getTrans());
        wor.setLand(land.getLand());
        wor.setLongitude(Location.getLon());
        wor.setLatitude(Location.getLat());
        wor.setName(Location.getName());

        if (ContextCompat.checkSelfPermission(Image.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(Image.this, new String[] {Manifest.permission.CAMERA}, 1);
        if (ContextCompat.checkSelfPermission(Image.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(Image.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        if (ContextCompat.checkSelfPermission(Image.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(Image.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        btnch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(Image.this);
            }
        });

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(biti!=null)
                    upload(biti);
                else
                    Toast.makeText(Image.this,"Please Select Image",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {

                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.WEBP, 100, stream);

                        biti = stream.toByteArray();

                        Bitmap bb = BitmapFactory.decodeByteArray(biti,0,biti.length);

                        imageView.setImageBitmap(bb);

                    }

                    break;
                case 1:
                    if(data!=null && data.getData() != null)
                    {
                        Uri uri= data.getData();
                        InputStream is = null;
                        try {
                            is = getContentResolver().openInputStream(uri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.WEBP,0,byteArrayOutputStream);

                        byte[] byteArray = byteArrayOutputStream.toByteArray();

                        biti = byteArray;

                        Bitmap bb = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                        imageView.setImageBitmap(bb);
                    }
                    break;
            }
        }
    }


    public void upload(byte[] bitmap) {

        UploadTask uploadTask=storageReference.child(Location.getName()).putBytes(bitmap);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Image.this,"FILE UPLOADED",Toast.LENGTH_SHORT).show();
                btnpro.setEnabled(true);
                btnpro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseReference.child("CASES").child(Location.getName()).setValue(wor);
                        startActivity(new Intent(Image.this,MapsActivity.class));
                    }
                });
            }
        })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Image.this,"UPLOAD FAILED",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
