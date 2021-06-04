package com.example.app_developement;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class security extends AppCompatActivity {
    CardView verifictaion , add , emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security);
        verifictaion = findViewById(R.id.verification);
        add = findViewById(R.id.add_contacts);
        emergency = findViewById(R.id.emergency);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(security.this, emergencyContacts.class);
                startActivity(in);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(security.this,add_contacts.class);
                startActivity(in);

            }
        });
        verifictaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(security.this , Adhar.class);
                startActivity(in);
            }
        });

    }

}
