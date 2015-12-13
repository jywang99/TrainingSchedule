package com.pineapple.trainingschedule2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Button button;
    TextView a;
    int b;
    EditText menuname;
    EditText memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu2);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        a = (TextView) findViewById(R.id.textView47);
        memo = (EditText) findViewById(R.id.memo);
        menuname = (EditText) findViewById(R.id.name);
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
            a.setText(" " + b + "回");
        }
    }

    public void increase(View V) {
        b++;
        a.setText(" " + b + "回");
    }

    public void commit(View v) {
        finish();
        SharedPreferences pref = getSharedPreferences("trainingmenu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("times", b);
        editor.putString("name", menuname.getText().toString());
        editor.putString("memo", memo.getText().toString());
        editor.commit();
    }

    public void editschedule(View V) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                        Log.d("test", String.format("%02d:%02d", hourOfDay,minute));
                    }
                },
                hour,minute,true);
        dialog.setTitle("開始時刻");
        dialog.setButton("決定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog.show();
    }

    public void weeklyschedule(View V){
        final CharSequence[]chkItems = {"月曜日","火曜日","水曜日","木曜日","金曜日","土曜日","日曜日"};
        final boolean[] chkSts = {false,false,false,false,false,false,false};
        AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
        checkDlg.setTitle("曜日選択");

        checkDlg.setMultiChoiceItems(
                chkItems,
                chkSts,
                new DialogInterface.OnMultiChoiceClickListener(){
                    public void onClick(DialogInterface dialog,int which,boolean flag){

                    }
                });
        checkDlg.setPositiveButton("決定",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
        checkDlg.create().show();
    }
}