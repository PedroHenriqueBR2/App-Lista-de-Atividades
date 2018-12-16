package com.droppages.pedrohenrique.afazeres.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {
    private Box<AtividadeDb> atividadeDbBox;
    private ListView lstAtividadesPendentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ObjectBox
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        atividadeDbBox = boxStore.boxFor(AtividadeDb.class);

        // Inflate ListView
        lstAtividadesPendentes = findViewById(R.id.lst_atividades_pendentes);
        inflateListView();
        // Select item click
        lstAtividadesPendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtId = view.findViewById(R.id.txt_id);
                showDetails(txtId.getText().toString());
            }
        });
    }

    private void inflateListView(){
        ArrayAdapter adapter = new AdapterListAtividade(this, fillList());
        lstAtividadesPendentes.setAdapter(adapter);
    }

    private void showDetails(String id){
        Intent intent = new Intent(this, ActivityDetails.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void newActivity(View view){
        startActivity(new Intent(this, NewActivity.class));
    }

    private ArrayList<AtividadeListItem> fillList(){
        ArrayList<AtividadeListItem> lista = new ArrayList<>();
        List<AtividadeDb> atividadeDbs = atividadeDbBox.getAll();

        for (AtividadeDb atividade : atividadeDbs){
            if (!atividade.isDone()){
                String id = Long.toString(atividade.getId());
                String priority = Integer.toString(atividade.getPriority());
                lista.add(new AtividadeListItem(id, priority ,atividade.getTitle(), atividade.getDescription(), atividade.getDeliveryDate()));
            }
        }
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_concluido:
                startActivity(new Intent(this, History.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        inflateListView();
        super.onResume();
    }
}
