package com.example.sebas.udemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.sebas.udemy.Adapters.MyAdapter;
import java.util.ArrayList;
import java.util.List;

public class Vistas extends AppCompatActivity {

    private ListView listView;
    List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistas);

        listView = (ListView)findViewById(R.id.listView);



        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");





        //para hacer uso del adapter de android se debe pasar un contexto,un layout y un ArrayList no  null
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        //Enlazamos el adaptados con nuestro listView
        listView.setAdapter(adapter);

        //Listener para ejecutar acciones cuando se seleccione un item

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Vistas.this,"Clicked item "+ names.get(position),Toast.LENGTH_SHORT).show();
            }
        });


        //enlazamos con nuestro adaptador personalizado
        MyAdapter myAdapter = new MyAdapter (this,R.layout.list_item,names);
        listView.setAdapter(myAdapter);
    }
}

