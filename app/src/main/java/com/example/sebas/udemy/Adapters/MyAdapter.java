package com.example.sebas.udemy.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sebas.udemy.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;
    }


    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //ViewHolder pattern
        ViewHolder viewHolder;

        //si los elementos no están refernciados
        if(convertView == null){

            //inflamos la vista que hemos cargado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView =layoutInflater.inflate(this.layout,null);

            viewHolder = new ViewHolder();

            //referenciamos los elementos del layout y lo rellenamos
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.textView);

            //guardo las referencias en un objeto
            convertView.setTag(viewHolder);

        }else{

            //los elementos están referenciados, recupero el objeto con las referencias
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //nos traemos el valor actual dependiente de la pocicion
        String currentName = names.get(position);
        //currentName = (String) getItem(position);

        //modificamos los elementos del layout inflado y referenciado
        viewHolder.nameTextView.setText(currentName);

        //retornamos la vista inflada
        return convertView;
    }

    static class ViewHolder{
        private TextView nameTextView;
    }
}
