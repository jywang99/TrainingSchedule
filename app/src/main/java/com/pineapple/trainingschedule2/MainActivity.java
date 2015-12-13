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

import com.astuetz.PagerSlidingTabStrip;
import com.pineapple.trainingschedule2.R;


public class MainActivity extends FragmentActivity {

    ViewPager pager;
    MyPagerAdapter adapter;
    PagerSlidingTabStrip tabs;
    Toolbar toolbar;
    TextView[] textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("Training Schedule");
        toolbar.setTitleTextColor(Color.WHITE);

        pager.setAdapter(adapter);
        tabs.setViewPager(pager);


    }
}

