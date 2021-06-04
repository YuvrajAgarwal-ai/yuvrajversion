package com.example.app_developement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.DateFormat;
import java.util.Calendar;

public class eve_teasing extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button submit_btn,button,ch;
    EditText name , location ,date ,description;
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
        setContentView(R.layout.activity_eve_teasing);
        button = (Button)findViewById(R.id.eve_pick);
        submit_btn = (Button)findViewById(R.id.eve_submit);
        name = (EditText)findViewById(R.id.eve_name);
        location = (EditText)findViewById(R.id.eve_location);
        date = (EditText)findViewById(R.id.eve_date);
        ch = (Button)findViewById(R.id.eve_choose);
        img = (ImageView)findViewById(R.id.eve_image);
        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        description = (EditText)findViewById(R.id.eve_description);
        reff = FirebaseDatabase.getInstance().getReference().child("Eve-Teasing");

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



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadTask!= null && uploadTask.isInProgress()){
                    Toast.makeText(eve_teasing.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else{
                    AddData();
                    Intent in = new Intent(eve_teasing.this , response_recorded.class);
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
        String Location = location.getText().toString().trim();
        String Description = description.getText().toString().trim();
        String Date = date.getText().toString().trim();
        data data = new data(Name,Location,Description,Date,imageid);
        Toast.makeText(eve_teasing.this ,"Report have been submited",Toast.LENGTH_SHORT).show();

        reff.child(String.valueOf(maxid+1)).setValue(data);

        StorageReference ref =mStorageRef.child(System.currentTimeMillis()+"."+getExtension(inquri));


        uploadTask= ref.putFile(inquri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //String downloadUrl =taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        Toast.makeText(eve_teasing.this,"image uploaded succesfuly ",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        EditText textView = (EditText)findViewById(R.id.eve_date);
        textView.setText(currentDateString);
    }
}
