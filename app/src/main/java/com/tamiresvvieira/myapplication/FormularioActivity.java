package com.tamiresvvieira.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.tamiresvvieira.myapplication.dao.AnimalDAO;
import com.tamiresvvieira.myapplication.dao.EspecieDAO;
import com.tamiresvvieira.myapplication.model.Animal;
import com.tamiresvvieira.myapplication.model.Especie;

import java.util.List;

/**
 * Created by android on 06/09/2018.
 */

public class FormularioActivity extends AppCompatActivity{

    private EditText etNome, etIdade;
    private Button btnSalvar;
    private Spinner spEspecie;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNomeAnimal);
        etIdade = (EditText) findViewById(R.id.etIdade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        spEspecie = (Spinner) findViewById(R.id.spEspecie);

        carregarEspecies();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void salvar(){
        String nome = etNome.getText().toString();

        if (nome.isEmpty() ||spEspecie.getSelectedItemPosition() == 0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle(getResources().getString(R.string.txtAtencao));
            alerta.setMessage(R.string.txtCamposObrigatorios);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }else {
            String idade = etIdade.getText().toString();
            if (idade.isEmpty())
                idade = "0";
            idade = idade.replace(",",".");
            double ida = Double.valueOf(idade);

            Animal ani = new Animal();
            ani.setNome(nome);
            ani.setIdade(ida);
            ani.setEspecie((Especie) spEspecie.getSelectedItem());

            AnimalDAO.inserir(this, ani);

            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.menu_nova_categoria ){
            cadastrarEspecie();
        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarEspecie(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle( getResources().getString(R.string.txtNovaEspecie) );
        alerta.setIcon( android.R.drawable.ic_menu_edit );

        final EditText etNomeEspecie = new EditText(this);
        etNomeEspecie.setHint( R.string.hintCategoria );
        etNomeEspecie.setTextColor(Color.GRAY);

        alerta.setView( etNomeEspecie );

        alerta.setNeutralButton(
                getResources().getString(R.string.txtCancelar) ,
                null );
        alerta.setPositiveButton(
                getResources().getString(R.string.txtSalvar),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nome = etNomeEspecie.getText().toString();
                        if ( ! nome.isEmpty() ){
                            Especie esp = new Especie();
                            esp.setNome( nome );
                            EspecieDAO.inserir(FormularioActivity.this, esp);
                            carregarEspecies();
                        }
                    }
                });
        alerta.show();

    }

    private void carregarEspecies(){

        List<Especie> lista = EspecieDAO.getEspecies(this);
        Especie fake = new Especie();
        fake.setId( 0 );
        fake.setNome( getResources().getString(R.string.txtSelecione) );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, lista);
        spEspecie.setAdapter(adapter);

    }

}
