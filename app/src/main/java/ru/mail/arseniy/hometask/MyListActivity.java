package ru.mail.arseniy.hometask;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MyListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<String> arrayList = new ArrayList<>();
        for (Integer i = 1; i<=1000000; i++) {
            arrayList.add(i.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_list,R.id.textView,arrayList);

        this.setListAdapter(adapter);


    }
}
