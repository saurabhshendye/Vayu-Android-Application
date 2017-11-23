package com.example.saurabhs.analyzerapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Saurabh on 11/15/2017.
 */

public class AirQualityProvider extends ContentProvider {

    private AirDBHelper airDBHelper;

    public static final String LOG_TAG = AirQualityProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the pets table */
    private static final int AIR_RECORDS = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int AIR_RECORD_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        sUriMatcher.addURI(AirContract.CONTENT_AUTHORITY, AirContract.PATH_AIR, AIR_RECORDS);

        sUriMatcher.addURI(AirContract.CONTENT_AUTHORITY, AirContract.PATH_AIR + "/#", AIR_RECORD_ID);

    }


    @Override
    public boolean onCreate() {
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        airDBHelper = new AirDBHelper(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case AIR_RECORDS:
                return AirContract.AirEntry.CONTENT_LIST_TYPE;
            case AIR_RECORD_ID:
                return AirContract.AirEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case AIR_RECORDS:
                return insertRecord(uri,contentValues);
            default:
                throw new IllegalArgumentException();
        }
    }

    private Uri insertRecord(Uri uri, ContentValues values){

        SQLiteDatabase db = airDBHelper.getWritableDatabase();
        long id = db.insert(AirContract.AirEntry.TABLE_NAME,null, values);
        if (id==-1){
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
