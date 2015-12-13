package com.pineapple.trainingschedule2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by junyiwang on 15/11/01.
 */
public class WeekFragment extends Fragment {

    int position;

    public static WeekFragment newInstance(int position) {

        WeekFragment f = new WeekFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        f.setArguments(b);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weekfragment, container, false);

        return view;
    }

}
