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

    static final String TABLE_NAME = "TrainingMenu";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pref = getSharedPreferences("idnum", MODE_PRIVATE);
    }

    public void insert(String name, int hr, int min, int times, String memo,
                       int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
        ContentValues val = new ContentValues();
        val.put("name", name);
        val.put("hr", hr);
        val.put("min", min);
        val.put("times", times);
        val.put("memo", memo);
        val.put("monday", monday);
        val.put("tuesday", tuesday);
        val.put("wednesday", wednesday);
        val.put("thursday", thursday);
        val.put("friday", friday);
        val.put("saturday", saturday);
        val.put("sunday", sunday);
        MainActivity.db.insert(TABLE_NAME, null, val);
//        pref = getSharedPreferences("idnum", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        int a = pref.getInt("idnum", 0);
//        a++;
//        editor.putInt("idnum", a);
//        editor.commit();
    }

    public String search(int idnum) {
        Cursor cursor = null;
        String result = "";

        try {
            cursor = MainActivity.db.query(TABLE_NAME,
                    new String[]{"name", "hr", "min", "times", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"},
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
