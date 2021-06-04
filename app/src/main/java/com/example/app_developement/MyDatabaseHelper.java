package com.example.app_developement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String TAG = "Contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COL1 = "ID";
    private static final String COL2 = "number";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context,TABLE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = " CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +  COL2 + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);


    }
    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        Log.d(TAG ,"addData: Adding" + item + "to" +TABLE_NAME);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return  false;
        }else
        {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }

    public  Cursor getItemId(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = " SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + number + "'";
       Cursor data = db.rawQuery(Query,null);
       return  data;
    }

    public void  updateName(String newName , int id , String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newName +
                "' WHERE " + COL1 + " = '" + id + "'" + " AND " + COL2 + " = '" +
                oldName + "'" ;
        db.execSQL(query);
    }


    public void deleteName(int id ,String number){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'" +  " AND " + COL2 + " = '" + number + "'";
        db.execSQL(query);
    }
}
