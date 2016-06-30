package ru.mail.arseniy.hometask;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class ListViewBaseAdapterActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        ListView lv = (ListView) findViewById(R.id.listview);


        ArrayList<String> numbers = new ArrayList<>();
        for (Integer i = 1; i<500000; i++) {
            numbers.add(i.toString());
        }
        lv.setAdapter(new MyAdapter(this, numbers.toArray(new String[0])));
    }


}
