package com.example.retrofit.repositories;


import static com.example.retrofit.repositories.DataBaseEnums.KEY_ID;
import static com.example.retrofit.repositories.DataBaseEnums.KEY_IMAGE_SRC;
import static com.example.retrofit.repositories.DataBaseEnums.KEY_IS_SELECTED;
import static com.example.retrofit.repositories.DataBaseEnums.KEY_PRICE;
import static com.example.retrofit.repositories.DataBaseEnums.KEY_TYPE;
import static com.example.retrofit.repositories.DataBaseEnums.TABLE_PROPERTY;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, DataBaseEnums.DATABASE_NAME, null, DataBaseEnums.DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
// Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PROPERTY + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_TYPE + " TEXT," +  KEY_IMAGE_SRC + " TEXT ," + KEY_PRICE + " INTEGER,"
                + KEY_IS_SELECTED + " INTEGER " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseEnums.TABLE_PROPERTY);

        // Create tables again
        onCreate(db);
    }



}