package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails (Name TEXT primary key, Phone TEXT,Email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }
     public Boolean insertuserdata(String Name, String Phone,String Email) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Phone",Phone);
        contentValues.put("Email",Email);
        long result = db.insert("Userdetails",null,contentValues);
        if (result==-1)
             return false;
        else
             return true;

     }



    public Cursor getdata(){
         SQLiteDatabase db=this.getWritableDatabase();
         Cursor cursor =db.rawQuery("Select * from Userdetails",null);
         return cursor;
     }


}
