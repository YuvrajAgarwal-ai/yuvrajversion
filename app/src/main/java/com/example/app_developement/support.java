package com.example.app_developement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class support  extends AppCompatActivity {
    CardView lost_found,eve , volunteer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        lost_found = findViewById(R.id.drawer_lost_found);
        eve = findViewById(R.id.drwaer_report);
        volunteer = findViewById(R.id.team);

        lost_found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(support.this , lost_found.class);
                startActivity(in);
            }
        });

        eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(support.this , eve_teasing.class);
                startActivity(in);
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(support.this , volunteer.class);
                startActivity(in);
            }
        });




    }
}
