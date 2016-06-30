package ru.mail.arseniy.hometask;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList names;

    private Resources res;
    private String[] hundreds;
    private String[] decades;
    private String[] units;
    private String[] thousand;
    private String[] thousandUnit;

    public String getHundred(String number, Boolean flag) {

        if (flag==Boolean.TRUE) {
            System.arraycopy(thousandUnit,0,units,1,2);
        }
        String[] teen = res.getStringArray(R.array.teen);
        String result = "";

        number = number.startsWith("000") ? "" : number;
        number = number.startsWith("00") ? number.substring(2) : number;
        number = number.startsWith("0") ? number.substring(1) : number;

        if (!number.equals("")) {
            if (number.length() > 2) {
                result += hundreds[Character.digit(number.charAt(0), 10) - 1] + " ";
                if (Character.digit(number.charAt(1), 10) == 1) {
                    result += teen[Character.digit(number.charAt(2), 10)] + " ";
                } else {
                    if (number.charAt(1) != '0')
                        result += decades[Character.digit(number.charAt(1) - 2, 10)] + " ";
                    if (number.charAt(2) != '0')
                        result += units[Character.digit(number.charAt(2), 10)] + " ";
                }
            } else {
                if (number.length() > 1) {
                    if (number.charAt(0) == '1') {
                        result += teen[Character.digit(number.charAt(1), 10)] + " ";
                    } else {
                        if (number.charAt(0) != '0')
                            result += decades[Character.digit(number.charAt(0) - 2, 10)] + " ";
                        if (number.charAt(1) != '0')
                            result += units[Character.digit(number.charAt(1), 10)] + " ";
                    }
                } else {
                    result += units[Character.digit(number.charAt(0), 10)] + " ";
                }
            }
        }

        return result;
    }

    public String getThousand(String number) {
        String result = "";
        Integer len = number.length();

        result += getHundred(number,Boolean.TRUE);
        if (len > 1) {
            if (number.charAt(len - 2) == '1') {
                result += thousand[2] + " ";
            }
        } else {
            Character ch = number.charAt(len-1);
            if (ch == '1') {
                result += thousand[0] + " ";
            } else if (ch >= '2' && ch <= '4') {
                result += thousand[1] + " ";
            } else {
                result += thousand[2] + " ";
            }
        }

        return result;
    }

    public String getNumber(String number) {

        Integer len = number.length();
        String result = "";
        if (len == 7) {
            result = res.getString(R.string.million);
        } else if (len > 3) {
            result += getThousand(number.substring(0, len - 3));
            result += getHundred(number.substring(len-3,len),Boolean.FALSE);
        } else {
            result += getHundred(number,Boolean.FALSE);
        }

        return result;
    }

    public static class ViewHolder {
        public TextView textView;
    }

    MyAdapter(Context context, ArrayList list) {
        this.context = context;
        names = list;
        res = context.getResources();
        hundreds = res.getStringArray(R.array.hundred);
        decades = res.getStringArray(R.array.decade);
        units = res.getStringArray(R.array.unit);
        thousand = res.getStringArray(R.array.thousand);
        thousandUnit = res.getStringArray(R.array.thousand_unit);


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
        String str = getNumber((String)names.get(position));
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
