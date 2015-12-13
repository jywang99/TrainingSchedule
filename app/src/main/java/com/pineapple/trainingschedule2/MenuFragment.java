package com.pineapple.trainingschedule2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by junyiwang on 15/11/01.
 */
public class MenuFragment extends Fragment {

    int position;

    public static MenuFragment newInstance(int position) {

        MenuFragment f = new MenuFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

        /*ListView listView = (ListView)getActivity().findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        SharedPreferences pref = getActivity().getSharedPreferences("trainingmenu", Context.MODE_PRIVATE);
        int times = pref.getInt("times",0);
        String times1 = String.valueOf(times);
        //adapter.add(times1);
        adapter.add("aaa");
        listView.setAdapter(adapter);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menufragment, container, false);
        View button = view.findViewById(R.id.plusbutton);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), AddMenuActivity.class);
                startActivity(intent);
            }
        });






        return view;
    }

}
