package com.tamiresvvieira.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.tamiresvvieira.myapplication.dao.AnimalDAO;
import com.tamiresvvieira.myapplication.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {


    private ListView lvLista;

    private List<Animal> listaDeAnimais;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvLista = (ListView) findViewById(R.id.lvLista);
        //----------Linhas para carregar o ListView-------

//        listaDeAnimais = new ArrayList<>();
//        adapter = new ArrayAdapter(ListaActivity.this,android.R.layout.simple_list_item_1,listaDeAnimais);
//        lvLista.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
                startActivity( intent );

//                listaDeProdutos.add( produto.getText().toString());
//                adapter.notifyDataSetChanged();
//                produto.setText("");
//                Snackbar.make(view, "Produto salvo", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {


                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaActivity.this);
                alerta.setTitle("Atenção");
                alerta.setIcon(android.R.drawable.ic_dialog_alert);
                alerta.setMessage("Confirma a exclusão do animal " + listaDeAnimais.get(position).getNome() + "?");
                alerta.setNeutralButton("cancelar", null);
                alerta.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
 //                       listaDeAnimais.remove( position );
//                        adapter.notifyDataSetChanged();
                        excluirAnimal(position);
                    }
                });
                alerta.show();



                return true;
            }
        });

        carregarAnimais();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarAnimais();

    }

    @Override


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

   private void excluirAnimal(int posicao){
       Animal ani = (Animal) lvLista.getItemAtPosition(posicao);
        AnimalDAO.excluir(this, ani.getId());
        carregarAnimais();

   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarAnimais(){
        listaDeAnimais = AnimalDAO.getAnimais(this);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeProdutos);
        AdapterAnimais adapter = new AdapterAnimais(this,listaDeAnimais);
        lvLista.setAdapter( adapter );
    }
}
