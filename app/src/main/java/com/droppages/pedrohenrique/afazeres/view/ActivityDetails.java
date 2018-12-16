package com.droppages.pedrohenrique.afazeres.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.droppages.pedrohenrique.afazeres.R;
import com.droppages.pedrohenrique.afazeres.controller.App;
import com.droppages.pedrohenrique.afazeres.model.AtividadeDb;
import com.droppages.pedrohenrique.afazeres.model.AtividadeDb_;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class ActivityDetails extends AppCompatActivity {
    private TextView txtTitulo, txtDesc, txtDateCadaster, txtDateDelivery;
    private BoxStore boxStore;
    private Box<AtividadeDb> dbBox;
    private String idDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Bind
        txtTitulo       = findViewById(R.id.txt_title);
        txtDesc         = findViewById(R.id.txt_description);
        txtDateDelivery = findViewById(R.id.txt_date_delivery);
        txtDateCadaster = findViewById(R.id.txtDateCadaster);

        // ObjectBox
        boxStore = ((App)getApplication()).getBoxStore();
        dbBox    = boxStore.boxFor(AtividadeDb.class);

        Intent intent = getIntent();
        idDetail = intent.getStringExtra("id");
        fillLabels();
    }

    private void fillLabels(){
        Long id = Long.parseLong(idDetail);
        AtividadeDb atividade = dbBox.get(id);
        txtTitulo.setText(atividade.getTitle());
        txtDateCadaster.setText(atividade.getRegisterDate());
        txtDesc.setText(atividade.getDescription());
        txtDateDelivery.setText(atividade.getDeliveryDate());
    }

    public void finalize(View view){
        Long id = Long.parseLong(idDetail);
        AtividadeDb atividade = dbBox.find(AtividadeDb_.id, id).get(0);
        atividade.setDone(true);
        dbBox.put(atividade);
        finish();
    }

    public void delete(View view){
        Long id = Long.parseLong(idDetail);
        dbBox.remove(id);
        finish();
    }

}
