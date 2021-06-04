package com.example.app_developement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private static  final String TAG = "ListDataActivity";
    MyDatabaseHelper myDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mListView = findViewById(R.id.ListView);
        myDatabaseHelper = new MyDatabaseHelper(this);
        populateListView();
    }
    private void populateListView(){
        Log.d(TAG , "populateListView: Displaying data in ListView");
        Cursor data = myDatabaseHelper.getData();
        ArrayList <String> ListData = new ArrayList<>();
        while (data.moveToNext()){
            ListData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ListData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String number = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick : You Clicked on" + number);
                Cursor data = myDatabaseHelper.getItemId(number);
                int itemId = -1;
                while (data.moveToNext()){
                    itemId = data.getInt(0);
                }
                if(itemId > -1){
                    Log.d(TAG , "onItemClick : The ID is" + itemId);
                    Intent in = new Intent(ListDataActivity.this , EditDataActivity.class);
                    in.putExtra("id",itemId);
                    in.putExtra("number",number);
                    startActivity(in);
                    finish();

                }else{
                    toastmessage("no id associated with that name");
                }
            }
        });

    }
    private  void toastmessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();   }
}
