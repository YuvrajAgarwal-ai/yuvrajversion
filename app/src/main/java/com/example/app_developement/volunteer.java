package com.example.app_developement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class volunteer extends AppCompatActivity {
    Button submit_btn,ch;
    EditText name , email , location  ,description;
    ImageView img;
    StorageReference mStorageRef;
    FirebaseDatabase database;
    DatabaseReference reff;
    int maxid = 0;
    private StorageTask uploadTask;
    public Uri inquri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        submit_btn = (Button)findViewById(R.id.volunteer_submit);
        name = (EditText)findViewById(R.id.volunteer_name);
        email= (EditText)findViewById(R.id.volunteer_email);
        location = (EditText)findViewById(R.id.volunteer_location);
        ch = (Button)findViewById(R.id.volunteer_choose);
        img = (ImageView)findViewById(R.id.volunteer_image);
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        description = (EditText)findViewById(R.id.volunteer_description);
        reff = FirebaseDatabase.getInstance().getReference().child("Volunteer");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadTask!= null && uploadTask.isInProgress()){
                    Toast.makeText(volunteer.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else{
                    AddData();
                    Intent in = new Intent(volunteer.this , response_recorded.class);
                    startActivity(in);
                }

            }
        });
    }
    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void FileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==RESULT_OK && data!= null && data.getData()!= null){
            inquri = data.getData();
            img.setImageURI(inquri);
        }
    }

    public  void  AddData(){

        String imageid;
        imageid = System.currentTimeMillis()+"."+getExtension(inquri);

        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Location = location.getText().toString().trim();
        String Description = description.getText().toString().trim();
        data_volunteer data_volunteer= new data_volunteer(Name,Email,Location,Description,imageid);
        Toast.makeText(volunteer.this ,"Report have been submited",Toast.LENGTH_SHORT).show();

        reff.child(String.valueOf(maxid+1)).setValue(data_volunteer);

        StorageReference ref =mStorageRef.child(System.currentTimeMillis()+"."+getExtension(inquri));


        uploadTask= ref.putFile(inquri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //String downloadUrl =taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        Toast.makeText(volunteer.this,"image uploaded succesfuly ",Toast.LENGTH_SHORT).show();
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
