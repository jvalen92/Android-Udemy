package com.example.sebas.udemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sebas.udemy.Adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridView_Activity extends AppCompatActivity {

    private GridView gridView;
    List<String> names = new ArrayList<String>();
    private int contador=0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_);

        gridView =findViewById(R.id.gridView);

        names.add("alejandro");
        names.add("Ferndando");
        names.add("Roberto");
        names.add("alejandro");


        //Listener para ejecutar acciones cuando se seleccione un item

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridView_Activity.this,"Clicked item "+ names.get(position),Toast.LENGTH_SHORT).show();
            }
        });


        //enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter (this,R.layout.grid_item,names);
        gridView.setAdapter(myAdapter);


        //Registrat nuestro context menu
        registerForContextMenu(gridView);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:

                //añadimos nuevo nombre
                this.names.add("añadido n: "+ (++contador));

                //notificamos al adaptador el cambio del contador
                this.myAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater menuInflater = getMenuInflater();

        //informacion del objeto seleccionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //Header del menu de opciones
        menu.setHeaderTitle(names.get(info.position));

        menuInflater.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //informacion del objeto seleccionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch (item.getItemId()){
            case R.id.delete_item:
                //eliminar el objeto seleccionado
                this.names.remove(info.position);
                //notificar al adapter que hubo cambios
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);

        }

    }
}
