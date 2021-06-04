package com.example.app_developement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class response_recorded extends AppCompatActivity {
    Button home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_recorded);
        home_page = (Button)findViewById(R.id.home_page);
        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(response_recorded.this,HomePage.class);
                startActivity(in);
            }
        });
    }
}
