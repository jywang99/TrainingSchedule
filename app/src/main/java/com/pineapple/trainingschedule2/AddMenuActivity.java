package com.pineapple.trainingschedule2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class AddMenuActivity extends ActionBarActivity {

    Button button2;
    Button button3;
    TextView a;
    int b = 0;
    EditText menuinput;
    EditText memoinput;
    SQLiteDatabase db;
    static final String TABLE_NAME = "TrainingMenu";
    SharedPreferences pref;
    String timeString;
    int hr;
    int min;
    int monday;
    int tuesday;
    int wednesday;
    int thursday;
    int friday;
    int saturday;
    int sunday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu2);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        a = (TextView) findViewById(R.id.textView47);
        memoinput = (EditText) findViewById(R.id.memo);
        menuinput = (EditText) findViewById(R.id.name);
        b = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void decrease(View V) {
        if (b > 0) {
            b--;
            a.setText(String.valueOf(b));
        }
    }

    public void increase(View V) {
        b++;
        a.setText(String.valueOf(b));
    }

    public void commit(View v) {
        String name = menuinput.getText().toString();
        String memo = memoinput.getText().toString();
        String t = a.getText().toString();
        int times = Integer.parseInt(t.substring(0,1));
        insert(name, hr, min, times, memo, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        finish();
//        SharedPreferences pref = getSharedPreferences("trainingmenu", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt("times", b);
//        editor.putString("name", menuname.getText().toString());
//        editor.putString("memo", memo.getText().toString());
//        editor.commit();
    }

    public void editschedule(View V) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        doAddEntry(db,11,11);
                        timeString = String.format("%02d:%02d", hourOfDay, minute);
                        hr = Integer.parseInt(timeString.substring(0, 2));
                        min = Integer.parseInt(timeString.substring(4, 5));
                    }
                },
                hour, minute, true);
        dialog.setTitle("開始時刻");
        dialog.setButton("決定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog.show();
    }

    public void weeklyschedule(View V) {
        final CharSequence[] chkItems = {"月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日", "日曜日"};
        final boolean[] chkSts = {false, false, false, false, false, false, false};
        AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
        checkDlg.setTitle("曜日選択");

        checkDlg.setMultiChoiceItems(
                chkItems,
                chkSts,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which, boolean flag) {


                    }
                });
        checkDlg.setPositiveButton("決定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        monday = asdf(chkSts[0]);
                        tuesday = asdf(chkSts[1]);
                        wednesday = asdf(chkSts[2]);
                        thursday = asdf(chkSts[3]);
                        friday = asdf(chkSts[4]);
                        saturday = asdf(chkSts[5]);
                        sunday = asdf(chkSts[6]);
                    }
                });
        checkDlg.create().show();
    }

    //    private void doAddEntry (int hour, int min){
//        hour = 1;
//        min = 1;
//
//
//    }
    public void insert(String name, int hr, int min, int times, String memo, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
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
            cursor = db.query(TABLE_NAME,
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
    public int asdf(boolean tf){
        int h;
        if(tf){
            h = 0;
        }else{
            h = 1;
        }
        return h;
    }
}