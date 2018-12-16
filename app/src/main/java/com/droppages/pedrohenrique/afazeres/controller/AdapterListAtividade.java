package com.droppages.pedrohenrique.afazeres.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.droppages.pedrohenrique.afazeres.R;
import com.droppages.pedrohenrique.afazeres.model.AtividadeListItem;

import java.util.ArrayList;

public class AdapterListAtividade extends ArrayAdapter {

    private ArrayList<AtividadeListItem> lista;
    private Context context;


    public AdapterListAtividade(Context context, ArrayList<AtividadeListItem> lista) {
        super(context, R.layout.line_main, lista);
        this.context = context;
        this.lista = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.line_main, parent, false);

            String id           = lista.get(position).getId();
            String priority     = lista.get(position).getPriority();
            String title        = lista.get(position).getTitle();
            String description  = lista.get(position).getDescription();
            String date         = lista.get(position).getDateCadaster();

            TextView txtId          = view.findViewById(R.id.txt_id);
            TextView txtPriority    = view.findViewById(R.id.txt_priority);
            TextView txtTitle       = view.findViewById(R.id.txt_title);
            TextView txtDescription = view.findViewById(R.id.txt_description);
            TextView txtDate        = view.findViewById(R.id.txt_date);

            txtId.setText(id);
            txtPriority.setText(priority);
            txtTitle.setText(title);
            txtDescription.setText(description);
            txtDate.setText(date);

            return view;
        }
        return v;
    }
}
