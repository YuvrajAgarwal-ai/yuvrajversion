package com.example.app_developement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_contacts extends AppCompatActivity {
    EditText editText;
    MyDatabaseHelper myDatabaseHelper;
    Button btnadd ,btnView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        btnadd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        editText = findViewById(R.id.editText);
        myDatabaseHelper = new MyDatabaseHelper(this);




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    toastMessage("you must put something");
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(add_contacts.this,ListDataActivity.class);
                startActivity(in);
                finish();
            }
        });




    }
    public  void AddData(String newEntry){
        boolean insertData = myDatabaseHelper.addData(newEntry);
        if (insertData){
            toastMessage("Data succesfully inserted");
        }else{
            toastMessage("something went wrong");
        }


    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
