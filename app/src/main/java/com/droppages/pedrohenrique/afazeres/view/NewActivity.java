package com.droppages.pedrohenrique.afazeres.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.droppages.pedrohenrique.afazeres.R;
import com.droppages.pedrohenrique.afazeres.controller.App;
import com.droppages.pedrohenrique.afazeres.model.AtividadeDb;
import com.droppages.pedrohenrique.afazeres.utils.Utilities;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class NewActivity extends AppCompatActivity {
    private EditText txtTitle, txtDesc, txtDate;
    private SeekBar skbPriority;
    private BoxStore boxStore;
    private Box<AtividadeDb> dbBox;
    private Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        txtTitle    = findViewById(R.id.edit_title);
        txtDesc     = findViewById(R.id.edit_desc);
        txtDate     = findViewById(R.id.edit_date);
        skbPriority = findViewById(R.id.skb_priority);

        boxStore = ((App)getApplication()).getBoxStore();
        dbBox = boxStore.boxFor(AtividadeDb.class);
    }

    private void cadasterNewActivity(){
        if (allFieldsAreOkay()){
            utilities = new Utilities();
            String title    = txtTitle.getText().toString().trim();
            String desc     = txtDesc.getText().toString().trim();
            String dateNow  = utilities.getDateNow(selectDateType());
            String date     = txtDate.getText().toString().trim();
            int priority    = skbPriority.getProgress();
            AtividadeDb atividadeDb = new AtividadeDb(title, desc, dateNow, date, priority, false);
            dbBox.put(atividadeDb);
            clearFields();
            showMessage("Salvo com sucesso!");
        }
    }

    private String selectDateType() {
        String date = txtDate.getText().toString().trim();
        if (date.charAt(2) == '.'){
            return "dd.MM.yyyy";
        } else if (date.charAt(2) == '-'){
            return "dd-MM-yyyy";
        }
        return "dd/MM/yyyy";
    }

    private boolean allFieldsAreOkay(){
        String title = txtTitle.getText().toString();
        String desc  = txtDesc.getText().toString();
        String date  = txtDate.getText().toString();

        if (title.trim().length() > 0 || desc.trim().length() > 0 || date.trim().length() > 0){
            if (dateVerify(date)){
                return true;
            } else {
                showMessage("Data inválida.");
            }
        } else {
            showMessage("Preencha todos os campos corretamente.");
        }
        return false;
    }

    private boolean dateVerify(String d){
        if (d.length() != 10){
            return false;
        }
        if (d.charAt(2) == '.' && d.charAt(5) == '.'){
            return true;
        } else if (d.charAt(2) == '-' && d.charAt(5) == '-'){
            return true;
        } else if (d.charAt(2) == '/' && d.charAt(5) == '/'){
            return true;
        }
        return false;
    }

    private void clearFields(){
        txtTitle.setText("");
        txtDesc.setText("");
        txtDate.setText("");
    }

    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadaster, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                cadasterNewActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
