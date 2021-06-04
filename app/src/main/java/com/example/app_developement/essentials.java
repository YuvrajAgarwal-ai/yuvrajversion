package com.example.app_developement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class essentials extends AppCompatActivity {
    CardView location , chalan , reservation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essentials);
        chalan=(CardView)findViewById(R.id.chalan);
        location = (CardView)findViewById(R.id.location);
        reservation = (CardView)findViewById(R.id.reservation);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(essentials.this,MapsActivity.class);
                startActivity(in);
            }
        });

        chalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(essentials.this,sos.class);
                startActivity(browserIntent);
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(essentials.this,calling.class);
                startActivity(in);
            }
        });



    }
}
