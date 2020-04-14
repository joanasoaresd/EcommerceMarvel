package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;

//CONTÉM CONTEÚDOS DE MANIPULAÇÃO DOS DADOS NO BANCO
public class DbDatabaseComic implements AddComic {

    private SQLiteDatabase db;
    private DbOpenHelper criarBanco;
    private static final String TAG_BD = "Informação DB: ";

    public DbDatabaseComic(Context context) {
        criarBanco = new DbOpenHelper(context);
    }

    //inserir dados no banco
    @Override
    public void addingComic(int id, String title, String descr, int page, double price, String thumbnail) {
        ContentValues valores;
        long resultado;

        //getWritableDatabase p/ leitura e escrita de dados
        db = criarBanco.getWritableDatabase();

        try {
            valores = new ContentValues();
            valores.put(DbOpenHelper.ID, id);
            valores.put(DbOpenHelper.TITLE, title);
            valores.put(DbOpenHelper.DESCRIPTION, descr);
            valores.put(String.valueOf(DbOpenHelper.PAGE_COUNT), page);
            valores.put(String.valueOf(DbOpenHelper.PRICE), price);
            valores.put(DbOpenHelper.THUMBNAIL, thumbnail);

            resultado = db.insert(DbOpenHelper.TABELA, null, valores);
            Log.i(TAG_BD, "Registro inserido com sucesso!");
            db.close();
        } catch (Exception e) {
            Log.i(TAG_BD, "Erro" + e.getMessage());

        }

    }

    @Override
    public ArrayList<Comics> carregarDados(){
        Cursor cursor;
        ArrayList<Comics>comicsAux = new ArrayList<Comics>();
        //cursor retornará estes campos
        String [] campos = {criarBanco.ID, criarBanco.TITLE, criarBanco.DESCRIPTION, String.valueOf(criarBanco.PAGE_COUNT), String.valueOf(criarBanco.PRICE), criarBanco.THUMBNAIL};
        // recebe o retorno com dados somente leitura
        db = criarBanco.getReadableDatabase();
        //cursor salva as info retornadas no bd
        cursor = db.query(criarBanco.TABELA, campos, null, null, null, null, null);
        //mover o conteudo para a primeira posição
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        //return cursor;
        return comicsAux;
    }



}
