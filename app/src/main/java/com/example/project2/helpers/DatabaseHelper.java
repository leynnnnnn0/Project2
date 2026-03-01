package com.example.project2.helpers;


import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "project2.db";

    public DatabaseHelper(@Nullable Context context){
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullname TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT, " +
                "role TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //======================CREATE=========================

    public boolean insertUser(String fullname, String email, String password, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", fullname);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", role);

        long result = db.insert("users", null, cv); // results returns the id of the row

        return  result != -1;
    }


    //======================READ==========================

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});

        cursor.close();
        return cursor.moveToFirst();

    }

    public int validateUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ? and password = ?", new String[]{email, password});

        if (cursor.moveToFirst()){
            cursor.close();
            return  cursor.getInt(0);
        }
        else {
            return -1;
        }
    }


    //======================UPDATE=========================

//    public boolean checkPassword(String email, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put("password", password);
//
//        long result = db.update("users", cv, "email = ?", new String[]{email});
//
//        return result > 0; // 0 failed, 1 successful;
//    }

    //======================DELETE=========================

//    public boolean deleteMenu(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        int deletedRow = db.delete("menu", "id = ?", new String[]{String.valueOf(id)});
//
//        return deletedRow > 0; // 0 failed, 1 successful;
//    }
}


