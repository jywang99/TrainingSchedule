package com.pineapple.trainingschedule2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by junyiwang on 16/01/31.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    static final String DB = "sqlite_sample.db";
    static final int DB_VERSION = 1;
    static final String TABLE_NAME = "TrainingMenu";

    int number;

    static final String CREATE_TABLE = "create table" + TABLE_NAME +
            "(id integer primary key autoincrement not null, name text not null," +
            " hr integer, min integer, date integer, times integer, memo integer," +
            " monday integer, tuesday integer, wednesday integer, thursday integer, " +
            "friday integer, saturday integer, sunday integer)";

    public MySQLiteOpenHelper(Context context) {
        super(context, DB, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e("TAG", CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}