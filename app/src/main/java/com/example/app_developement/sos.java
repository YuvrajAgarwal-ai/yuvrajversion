package com.example.app_developement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class sos extends AppCompatActivity {
    Button choose , upload,image ;
    EditText name ;
    CheckBox c1 , c2 ,c3 , c4 ,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17;
    FirebaseDatabase database;
    ImageView  Image_upload;
    VideoView vedio;
    DatabaseReference reference;
    chalan_report report;
    int i = 0 ;
    public Uri inquri,vedioUri;
    private StorageTask uploadTask,vedioTask;
    StorageReference mStorageRef;
    MediaController mediaController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        c1 = findViewById(R.id.general);
        c2 = findViewById(R.id.un);
        c3 = findViewById(R.id.dis);
        c4 = findViewById(R.id.license);
        c5 = findViewById(R.id.over);
        c6 = findViewById(R.id.rash);
        c7 = findViewById(R.id.alcohol);
        c8 = findViewById(R.id.unfit);
        c9 = findViewById(R.id.uninsured);
        c10 = findViewById(R.id.speed);
        c11 = findViewById(R.id.permit);
        c12 = findViewById(R.id.overloading);
        c13 = findViewById(R.id.seatbelt);
        c14 = findViewById(R.id.two);
        c15 = findViewById(R.id.helmet);
        c16 = findViewById(R.id.juve);
        c17 = findViewById(R.id.officer);

        choose = findViewById(R.id.choose);
        upload = findViewById(R.id.upload);
        image = findViewById(R.id.choose_image);
        Image_upload = findViewById(R.id.image3);
        vedio = findViewById(R.id.vedio);
        name = findViewById(R.id.sos_name);
        report = new chalan_report();

        reference = FirebaseDatabase.getInstance().getReference().child("violation");
        mStorageRef = FirebaseStorage.getInstance().getReference("traffic_violation");
        mediaController = new MediaController(this);

        vedio.setMediaController(mediaController);
        mediaController.setAnchorView(vedio);
        vedio.start();




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if(dataSnapshot.exists()){
                  i = (int)dataSnapshot.getChildrenCount();
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVedio();
            }
        });





        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              violation();
              image_upload();
              vedio_upload();
              Toast.makeText(sos.this ,"Report have been submited",Toast.LENGTH_SHORT).show();
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==RESULT_OK && data!= null && data.getData()!= null){
            inquri = data.getData();
            Image_upload.setImageURI(inquri);
        }
        if(requestCode == 2 && resultCode ==RESULT_OK && data!= null && data.getData()!= null){
           vedioUri = data.getData();
           vedio.setVideoURI(vedioUri);
        }
    }

    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    public void  violation(){
        final String t1 = "Travel without ticket";
        final String t2 = "Driving an Unauthorized Vehicle without License";
        final String t3 = "Driving With Disqualified License";
        final String t4 = "Driving Without License";
        final String t5 = "Overspeeding";
        final String t6 = "Rash Driving";
        final String t7 = "Driving Under Influence of Alcohol or Intoxicating Substance";
        final String t8 = "Driving When Mentally/Physically Unfit";
        final String t9 = "Driving Uninsured Vehicle (without valid Insurance)";
        final String t10 = "Racing and Speed-testing";
        final String t11 = "Vehicle Without Permit";
        final String t12 = "Overloading";
        final String t13 = "Not Wearing Seatbelt";
        final String t14 = "Overloading of Two-Wheelers";
        final String t15 = "Not Wearing Helmet";
        final String t16 = "Offences by Juveniles";
        final String t17 = "Offences Committed by Enforcing Officers";

        if(c1.isChecked()){
            report.setViolation1(t1);
            reference.child(String.valueOf(i+1)).setValue(report);
        }else{

        }

        if(c2.isChecked()){
            report.setViolation2(t2);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c3.isChecked()){
            report.setViolation3(t3);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c4.isChecked()){
            report.setViolation4(t4);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c5.isChecked()){
            report.setViolation5(t5);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c6.isChecked()){
            report.setViolation6(t6);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c7.isChecked()){
            report.setViolation7(t7);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c8.isChecked()){
            report.setViolation8(t8);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c9.isChecked()){
            report.setViolation9(t9);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c10.isChecked()){
            report.setViolation10(t10);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c11.isChecked()){
            report.setViolation11(t11);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c12.isChecked()){
            report.setViolation12(t12);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c13.isChecked()){
            report.setViolation13(t13);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c14.isChecked()){
            report.setViolation14(t14);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c15.isChecked()){
            report.setViolation15(t15);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c16.isChecked()){
            report.setViolation16(t16);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }
        if(c17.isChecked()){
            report.setViolation17(t17);
            reference.child(String.valueOf(i+1)).setValue(report);

        }else{

        }


    }

    public void image_upload(){
        String Name = name.getText().toString().trim();
        report.setName(Name);
        String imageid;
        imageid = System.currentTimeMillis()+"."+getExtension(inquri);

        report.setImageId(imageid);
        reference.child(String.valueOf(i+1)).setValue(report);
        StorageReference ref =mStorageRef.child(System.currentTimeMillis()+"."+getExtension(inquri));


        uploadTask= ref.putFile(inquri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //String downloadUrl =taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        Toast.makeText(sos.this,"image uploaded succesfuly ",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }

    public void chooseVedio(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,2);
    }
    public String getfileext(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(vedioUri));
    }

    public void vedio_upload(){
        String vedioId ;
        vedioId = System.currentTimeMillis()+ "."+ getfileext(vedioUri);
        report.setVedioId(vedioId);
        reference.child(String.valueOf(i+1)).setValue(report);
        StorageReference ref =mStorageRef.child(System.currentTimeMillis()+"."+getExtension(vedioUri));


        vedioTask= ref.putFile(vedioUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //String downloadUrl =taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        Toast.makeText(sos.this,"vedio uploaded succesfuly ",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }






}
