package com.droppages.pedrohenrique.afazeres.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.droppages.pedrohenrique.afazeres.R;
import com.droppages.pedrohenrique.afazeres.controller.AdapterListAtividade;
import com.droppages.pedrohenrique.afazeres.controller.App;
import com.droppages.pedrohenrique.afazeres.model.AtividadeDb;
import com.droppages.pedrohenrique.afazeres.model.AtividadeListItem;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class History extends AppCompatActivity {
    private BoxStore boxStore;
    private Box<AtividadeDb> dbBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // ObjectBox
        boxStore = ((App)getApplication()).getBoxStore();
        dbBox    = boxStore.boxFor(AtividadeDb.class);

        ListView lista = findViewById(R.id.lst_atividades_concluidas);
        ArrayAdapter adapter = new AdapterListAtividade(this, fillList());
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtId = view.findViewById(R.id.txt_id);
                delete(txtId.getText().toString());
            }
        });

    }

    private void delete(String d){
        Long id = Long.parseLong(d);
        dbBox.remove(id);
        finish();
    }

    private ArrayList<AtividadeListItem> fillList(){
        ArrayList<AtividadeListItem> lista = new ArrayList<>();
        List<AtividadeDb> atividadeDbs = dbBox.getAll();

        for (AtividadeDb atividade : atividadeDbs){
            if (atividade.isDone()){
                String id = Long.toString(atividade.getId());
                String priority = Integer.toString(atividade.getPriority());
                lista.add(new AtividadeListItem(id ,priority ,atividade.getTitle(), atividade.getDescription(), atividade.getDeliveryDate()));
            }
        }
        return lista;
    }
}
