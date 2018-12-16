package com.droppages.pedrohenrique.afazeres.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // ObjectBox
        boxStore = ((App)getApplication()).getBoxStore();
        dbBox    = boxStore.boxFor(AtividadeDb.class);

        lista = findViewById(R.id.lst_atividades_concluidas);
        inflateListView();
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtId = view.findViewById(R.id.txt_id);
                verifyRequest(txtId.getText().toString());
                return false;
            }
        });
    }

    private void inflateListView(){
        ArrayAdapter adapter = new AdapterListAtividade(this, fillList());
        lista.setAdapter(adapter);
    }

    private void verifyRequest(final String d){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_alert);
        builder.setTitle("Atenção");
        builder.setMessage("Deletar registro selecionado?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Long id = Long.parseLong(d);
                dbBox.remove(id);
                inflateListView();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage("Cancelado");
            }
        });
        builder.create().show();
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

    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
