package com.example.saurabhs.analyzerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Saurabh on 11/15/2017.
 */

public class AirDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "analyzer.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_QUERY = "CREATE TABLE " + AirContract.AirEntry.TABLE_NAME +
            "( " + AirContract.AirEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            AirContract.AirEntry.COLUMN_TEMPERATURE + " TEXT," +
            AirContract.AirEntry.COLUMN_HUMIDITY + " TEXT, " +
            AirContract.AirEntry.COLUMN_CO + " TEXT, " +
            AirContract.AirEntry.COLUMN_PARTICULATES + " TEXT, " +
            AirContract.AirEntry.COLUMN_MB + " TEXT);";

    public AirDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
