package com.pineapple.trainingschedule2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyiwang on 15/11/01.
 */
public class MenuFragment extends Fragment {
    MySQLiteOpenHelper mySQLiteOpenHelper;
    SQLiteDatabase db;
    static final String TABLE_NAME = "TrainingMenu";

    int position;

   ArrayList menu ;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menufragment, container, false);
        View button = view.findViewById(R.id.plusbutton);

        menu = new ArrayList();


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                Intent intent = new Intent(getActivity(), AddMenuActivity.class);
                startActivity(intent);
            }
        });

//        for(int i = 0;i<1;i++){
//            menu.add(search(1));
//
//        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, menu);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        return view;
    }
    public String search(int idnum) {
        Cursor cursor = null;
        String result = "";

        try {
            cursor = db.query(TABLE_NAME,
                    new String[]{"id","name", "hr", "min", "times", "date"},
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
