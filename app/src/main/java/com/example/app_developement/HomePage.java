package com.example.app_developement;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class HomePage extends AppCompatActivity  {
    GridLayout mainGrid;
    CardView security,essentials,support;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth fAuth;
    Toolbar toolbar;
    MyDatabaseHelper myDatabaseHelper;
    ImageView sos ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        drawerLayout=  findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.toolbar);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this ,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();


        security =findViewById(R.id.security);
        essentials = findViewById(R.id.essentials);
        support = findViewById(R.id.support);
        sos = findViewById(R.id.sos);
        myDatabaseHelper = new MyDatabaseHelper(this);





        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this,security.class);
                startActivity(in);
            }
        });

        essentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, essentials.class);
                startActivity(in);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this,support.class);
                startActivity(in);
            }
        });


        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });









        fAuth = FirebaseAuth.getInstance();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_logout:
                        Logout();
                        return true;
                    case R.id.nav_rules:
                        Intent in = new Intent(HomePage.this , rules.class);
                        startActivity(in);
                        return  true;

                    case R.id.nav_traffic:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.trackschoolbus.com/blog/traffic-violations-penalties-india/"));
                        startActivity(browserIntent);

                        return  true;
                    case R.id.about_us:
                       in = new Intent(HomePage.this , aboutUS.class);
                       startActivity(in);

                        return  true;

                    case R.id.faq:
                        in = new Intent(HomePage.this , faq.class);
                        startActivity(in);

                        return  true;


                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });






    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private  void  Logout(){
        fAuth.signOut();
        finish();
        startActivity(new Intent(HomePage.this,MainActivity.class));

    }

    public void sendSMS(){
        Cursor data = myDatabaseHelper.getData();
        ArrayList<String> ListData = new ArrayList<>();
        while (data.moveToNext()){
            ListData.add(data.getString(1));
        }
        for (int i = 0 ;i <ListData.size();i++){
            String a = ListData.get(i);


            SmsManager sms = SmsManager.getDefault(); // using android SmsManager
            sms.sendTextMessage(a, null, "NeedHelp", null, null);
        }
        Toast.makeText(HomePage.this,"SOS has been sent",Toast.LENGTH_SHORT).show();


    }





    }
