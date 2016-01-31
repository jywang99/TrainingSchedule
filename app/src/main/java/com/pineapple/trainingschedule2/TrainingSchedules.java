package com.pineapple.trainingschedule2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TrainingSchedules extends AppCompatActivity {
    MySQLiteOpenHelper mySQLiteOpenHelper;
    SQLiteDatabase db;
    static final String TABLE_NAME = "TrainingMenu";
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext());
        db = mySQLiteOpenHelper.getWritableDatabase();


        pref = getSharedPreferences("idnum",MODE_PRIVATE);
    }

    public void insert(String name, int hr, int min, int times, int date) {
        ContentValues val = new ContentValues();
        val.put("name", name);
        val.put("hr", hr);
        val.put("min", min);
        val.put("times", times);
        val.put("date", date);
        db.insert(TABLE_NAME, null, val);
        pref = getSharedPreferences("idnum",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int a = pref.getInt("idnum",0);
        a++;
        editor.putInt("idnum",a);
        editor.commit();
    }

    public String search(int idnum) {
        Cursor cursor = null;
        String result = "";

        try {
            cursor = db.query(TABLE_NAME,
                    new String[]{"name", "hr", "min", "times", "date"},
                    "id = ?", new String[]{"" + idnum},
                    null, null, null);

            int indexName = cursor.getColumnIndex("name");
            int indexHr = cursor.getColumnIndex("hr");
            int indexMin = cursor.getColumnIndex("min");
            int indexTimes = cursor.getColumnIndex("times");
            int indexDate = cursor.getColumnIndex("date");

            while (cursor.moveToNext()) {
                String name = cursor.getString(indexName);
                int hr = cursor.getInt(indexHr);
                int min = cursor.getInt(indexMin);
                int times = cursor.getInt(indexTimes);
                int date = cursor.getInt(indexDate);
                result += date + "の" + hr + "時" + min + "分" + "から、" + name + "を" + times + "回やる！";
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }
}
