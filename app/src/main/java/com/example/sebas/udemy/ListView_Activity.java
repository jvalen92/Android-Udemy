package com.example.sebas.udemy;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.sebas.udemy.Adapters.MyAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListView_Activity extends AppCompatActivity {

    private ListView listView;
    List<String> names = new ArrayList<String>();
    private int contador=0;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistas);

        listView = (ListView)findViewById(R.id.listView);

        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");



        //enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter (this,R.layout.list_item,names);
        listView.setAdapter(myAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.list_item,null);
        builder.setView(convertView);
        builder.setCancelable(false);
        ListView listView = (ListView) convertView.findViewById(R.id.listView);
        builder.setTitle("holi");


        builder.show();
    }
}

