package com.pineapple.trainingschedule2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by junyiwang on 15/11/01.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    final String[] TITLES = {"Week", "Month", "Menu",};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return WeekFragment.newInstance(position);
            case 1:
                return MonthFragment.newInstance(position);
            case 2:
                return MenuFragment.newInstance(position);
            default:
                return MonthFragment.newInstance(position);
        }
    }

}


