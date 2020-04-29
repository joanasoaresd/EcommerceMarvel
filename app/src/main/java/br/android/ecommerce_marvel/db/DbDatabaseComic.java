package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;

//CONTÉM CONTEÚDOS DE MANIPULAÇÃO DOS DADOS NO BANCO
public class DbDatabaseComic implements DAO {

    private SQLiteDatabase db;
    private DbOpenHelper criarBanco;
    private static final String TAG_BD = "Informação DB: ";
    //  private Context context ;

    public DbDatabaseComic(Context context) {
        criarBanco = new DbOpenHelper(context);
    }

    //inserir dados no banco
    @Override
    public void inserirDados(Comics comics, int quant) {
        ContentValues valores = new ContentValues();
        //int id, String title, String descr, int page, double price
        long resultado;

        //getWritableDatabase p/ leitura e escrita de dados
        db = criarBanco.getWritableDatabase();

        valores.put(DbOpenHelper.ID, comics.getTitle());
        valores.put(DbOpenHelper.TITLE, comics.getTitle());
        valores.put(DbOpenHelper.DESCRIPTION, comics.getDescription());
        valores.put(DbOpenHelper.PAGE_COUNT, comics.getPageCount());
        valores.put(DbOpenHelper.PRICE, comics.getPrice());
        valores.put(DbOpenHelper.THUMBNAIL, comics.getThumb());
        valores.put(DbOpenHelper.QTDE, quant);

        resultado = db.insert(DbOpenHelper.TABELA, null, valores);
        if (resultado == -1)
            Log.i(TAG_BD, "Erro ao inserir registro. ");
        else
            Log.i(TAG_BD, "Registro inserido com sucesso!");
        // return "Registro Inserido com sucesso”;

    }

    //select
    @Override
    public ArrayList<Comics> carregarDados() {

       // itemLiswtView();
        //getReadable database leitura de dados.
        SQLiteDatabase db = criarBanco.getReadableDatabase();
        ArrayList<Comics> aux = new ArrayList<>();



        String sql = "SELECT id, title, description, page_count, price FROM " + DbOpenHelper.TABELA;

//new String[]{}
        Cursor cursor = db.rawQuery(sql, null);


        if (cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TITLE));
                int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.ID));
                int pageCount = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.PAGE_COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.PRICE));
                String desc = cursor.getString(cursor.getColumnIndex(DbOpenHelper.DESCRIPTION));
                String thumbnail = cursor.getString(cursor.getColumnIndex(DbOpenHelper.THUMBNAIL));
                aux.add(new Comics(id, title, desc, pageCount, price, thumbnail));

                int qtde = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.QTDE));

            } while (cursor.moveToNext());
        }

        db.close();
        return aux;
    }



}