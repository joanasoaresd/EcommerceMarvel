package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Thumbnail;

//CONTÉM CONTEÚDOS DE MANIPULAÇÃO DOS DADOS NO BANCO
public class DbDatabaseComic implements AddComic {

    private SQLiteDatabase db;
    private DbOpenHelper criarBanco;
    private static final String TAG_BD = "Informação DB: ";
    //  private Context context ;

    public DbDatabaseComic(Context context) {
        criarBanco = new DbOpenHelper(context);
    }

    //inserir dados no banco
    @Override
    public void inserirDados(int id, String title, String descr, int page, double price) {
        ContentValues valores = new ContentValues();
        //int id, String title, String descr, int page, double price
        long resultado;

        //getWritableDatabase p/ leitura e escrita de dados
        db = criarBanco.getWritableDatabase();

        valores.put(DbOpenHelper.ID, id);
        valores.put(DbOpenHelper.TITLE, title);
        valores.put(DbOpenHelper.DESCRIPTION, descr);
        valores.put(DbOpenHelper.PAGE_COUNT, page);
        valores.put(DbOpenHelper.PRICE, price);
        // valores.put(DbOpenHelper.THUMBNAIL, thumbnail);

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
        // int id, page;
        // String title, description, thumbnail;
        //  double price;
        ArrayList<Comics> aux = new ArrayList<>();
        String sql = "SELECT * FROM " + DbOpenHelper.TABELA;

        SQLiteDatabase db = criarBanco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TITLE));
                int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.ID));
                int pageCount = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.PAGE_COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.PRICE));
                String desc = cursor.getString(cursor.getColumnIndex(DbOpenHelper.DESCRIPTION));
                aux.add(new Comics(id, title, desc, pageCount, price));

            } while (cursor.moveToNext());
        }

        db.close();
        return aux;
    }


}