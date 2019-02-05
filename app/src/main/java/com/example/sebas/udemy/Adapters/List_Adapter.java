package com.example.sebas.udemy.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebas.udemy.DataObj.SingleRow;
import com.example.sebas.udemy.R;

import java.util.ArrayList;

public class List_Adapter extends BaseAdapter {

    ArrayList<SingleRow> rows;
    Context context;
    String [] names;
    int [] images;

    //constructor
    public List_Adapter(Context context, String [] names,int [] images){
        rows = new ArrayList<>();
        this.context=context;
        this.names=names;
        this.images=images;

        //Resources res = context.getResources();
        //String [] names = res.getStringArray(R.array.types);
        //int [] images = {R.drawable.clock,R.drawable.money,R.drawable.timetable};

        for (int i = 0; i<names.length ; i++) {
            rows.add(new SingleRow(names[i],images[i]));
        }
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Object getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_alert,parent,false);

        TextView textView = view.findViewById(R.id.alert_item);
        ImageView icon = view.findViewById(R.id.alert_icon);
        SingleRow singleRow = rows.get(position);

        textView.setText(singleRow.getName());
        icon.setImageResource(singleRow.getImage());

        return view;
    }
}
