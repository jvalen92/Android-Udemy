package com.example.sebas.udemy;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebas.udemy.Adapters.List_Adapter;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class Alertas extends AppCompatActivity {


    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

    }

    public void open_dialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_layout,null);
        ListView listView = (ListView) row.findViewById(R.id.alert_list);
        final String [] names = {"Express","Economico", "Programado"};
        int [] images = {R.drawable.clock,R.drawable.money,R.drawable.timetable};
        listView.setAdapter(new List_Adapter(this,names,images));
        builder.setView(row);
        final AlertDialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Alertas.this,names[position],Toast.LENGTH_LONG).show();
                TextView textView = findViewById(R.id.aleert_textView);
                textView.setText(names[position]);
                dialog.cancel();
            }
        });


    }

}


