package com.pineapple.trainingschedule2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pineapple.trainingschedule2.R;

/**
 * Created by junyiwang on 15/11/01.
 */
public class MonthFragment extends Fragment {

    int position;

    public static MonthFragment newInstance(int position) {

        MonthFragment f = new MonthFragment();
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
        View view = inflater.inflate(R.layout.monthfragment, container, false);

        return view;
    }

}
