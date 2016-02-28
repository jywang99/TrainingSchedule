package com.pineapple.trainingschedule2;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.astuetz.PagerSlidingTabStrip;
import com.pineapple.trainingschedule2.R;

import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends FragmentActivity {
    ViewPager pager;
    MyPagerAdapter adapter;
    PagerSlidingTabStrip tabs;
    Toolbar toolbar;
    TextView[] textview;
    int number = 0;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext());
        db = mySQLiteOpenHelper.getWritableDatabase();

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Training Schedule");
        toolbar.setTitleTextColor(Color.WHITE);

        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }
}


