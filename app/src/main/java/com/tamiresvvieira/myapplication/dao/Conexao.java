package com.tamiresvvieira.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by android on 10/09/2018.
 */

public class Conexao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "appAnimal";
    private static final int VERSAO_BANCO = 1;

    public Conexao(Context contexto){
        super(contexto, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS especies ( " +
                        "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                        "  nome TEXT NOT NULL ) "
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS animais ( " +
                        "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                        "  nome TEXT NOT NULL  , " +
                        "  idade DOUBLE , " +
                        "  codEspecie INTEGER ) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {

    }
}
