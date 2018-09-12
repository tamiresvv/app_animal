package com.tamiresvvieira.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tamiresvvieira.myapplication.model.Animal;
import com.tamiresvvieira.myapplication.model.Especie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 10/09/2018.
 */

public class AnimalDAO {

    public static void inserir(Context contexto, Animal animal){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", animal.getNome());
        valores.put("idade", animal.getIdade());
        valores.put("codEspecie", animal.getEspecie().getId());

        banco.insert("animais", null, valores);

    }

    public static List<Animal> getAnimais(Context contexto){
        List<Animal> listaDeAnimais = new ArrayList<>();
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();
        conn.onCreate(banco);
        Cursor tabela = banco.rawQuery
                ("SELECT a.id, a.nome, a.idade, a.codEspecie, e.nome  "
                        + "FROM animais a                                  "
                        + "INNER JOIN especies e                           "
                        + "ON a.codEspecie = e.id                          "
                        + "ORDER BY a.nome                                 "
                        , null);
        if ( tabela.getCount() > 0 ){
            tabela.moveToFirst();
            do {
                Especie esp = new Especie();
                esp.setId( tabela.getInt(3));
                esp.setNome(tabela.getString(4));

                Animal ani = new Animal();
                ani.setId( tabela.getInt(0) );
                ani.setNome( tabela.getString(1) );
                ani.setIdade( tabela.getDouble(2) );
                ani.setEspecie( esp );

                listaDeAnimais.add( ani );

            }while ( tabela.moveToNext() );
        }

        return listaDeAnimais;
    }

    public static void  excluir( Context contexto, int codAnimal ){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        banco.delete("animais", "id = " +
                "" + codAnimal, null);
    }

}
