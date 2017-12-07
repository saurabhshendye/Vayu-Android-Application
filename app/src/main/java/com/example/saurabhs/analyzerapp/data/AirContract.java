package com.example.saurabhs.analyzerapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Saurabh on 11/15/2017.
 */

public final class AirContract {

    public static final String CONTENT_AUTHORITY = "com.example.saurabhs.analyzerapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_AIR = "Air_Quality";

    public static final class AirEntry implements BaseColumns{
        /**
         * URI details
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_AIR);


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_AIR;

        /**
         * The MIME type of the {@link} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_AIR;

        /**
         * Define Constants for Air Quality Database
         */
        public static final String TABLE_NAME = "Air_Quality";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_PARTICULATES25 = "Particles25";
        public static final String COLUMN_PARTICULATES10 = "Particles10";
        public static final String COLUMN_CO = "CO";
        public static final String COLUMN_LPG = "LPG";                // Methane and Butane
        public static final String COLUMN_SMOKE = "Smoke";
    }
}
