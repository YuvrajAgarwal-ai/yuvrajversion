package com.example.app_developement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";
    Button btnsave ,btndelete,btnHome;
    EditText editText;
    MyDatabaseHelper myDatabaseHelper;
    String selectedName;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnsave = findViewById(R.id.btnsave);
        btndelete = findViewById(R.id.btndelete);
        editText = findViewById(R.id.editText);
        btnHome = findViewById(R.id.btnHome);
        myDatabaseHelper = new MyDatabaseHelper(this);


        Intent recievedIntent = getIntent();

        selectedID = recievedIntent.getIntExtra("id",-1);
        selectedName = recievedIntent.getStringExtra("number");

        editText.setText(selectedName);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString();
                if( !item.equals("")){
                    myDatabaseHelper.updateName(item,selectedID,selectedName);

                }else{
                    toastMessage("you must enter a number");
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.deleteName(selectedID,selectedName);
                editText.setText("");
                toastMessage("removed from database");
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EditDataActivity.this,HomePage.class);
                startActivity(in);
                finish();
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
