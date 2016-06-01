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

public class ListViewBaseAdapterActivity extends Activity {

    public String getHundred(String number) {

        Resources res = getResources();
        String[] hundreds = res.getStringArray(R.array.hundred);
        String[] decades = res.getStringArray(R.array.decade);
        String[] units = res.getStringArray(R.array.unit);
        String[] teen = res.getStringArray(R.array.teen);
        String result = "";

        if (number.length() > 2) {
            result += hundreds[Character.digit(number.charAt(0), 10)-1] + " ";
            if (Character.digit(number.charAt(1), 10) == 1) {
                result += teen[Character.digit(number.charAt(2), 10)] + " ";
            } else {
                if (number.charAt(1) != '0')
                    result += decades[Character.digit(number.charAt(1)-2, 10)] + " ";
                if (number.charAt(2) != '0')
                    result += units[Character.digit(number.charAt(2),10)] + " ";
            }
        } else {
            if (number.length() > 1) {
                if (number.charAt(0) == '1') {
                    result += teen[Character.digit(number.charAt(1), 10)] + " ";
                } else {
                    if (number.charAt(0) != '0')
                        result += decades[Character.digit(number.charAt(0)-2, 10)] + " ";
                    if (number.charAt(1) != '0')
                        result += units[Character.digit(number.charAt(1),10)] + " ";
                }
            } else {
                result += units[Character.digit(number.charAt(0),10)] + " ";
            }
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        ListView lv = (ListView) findViewById(R.id.listview);

        ArrayList<String> numbers = new ArrayList<>();
        for (Integer i = 1; i<=999; i++) {
            numbers.add(getHundred(i.toString()));
        }
        //String[] numbers = getResources().getStringArray(R.array.numbers);
        lv.setAdapter(new MyAdapter(this, numbers.toArray(new String[0])));

    }

    static class MyAdapter extends BaseAdapter {

        Context context;
        ArrayList names;

        public static class ViewHolder {
            public TextView textView;
        }

        MyAdapter(Context context, String[] list) {
            this.context = context;
            names = new ArrayList<>();
            Collections.addAll(names, list);

        }

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return names.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = (String) getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.textView = (TextView)convertView.findViewById(R.id.element_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText(str);
            if ((position & 1) == 1) {
                convertView.setBackgroundColor(Color.rgb(170, 170, 170));
            } else {
                convertView.setBackgroundColor(Color.rgb(255, 255, 255));
            }
            return convertView;
        }
    }
}
