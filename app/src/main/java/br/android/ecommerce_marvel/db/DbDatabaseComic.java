package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Script;
import android.util.Log;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;

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

        if (temId(comics.getId())) {
            valores.put(DbOpenHelper.ID, comics.getTitle());
            valores.put(DbOpenHelper.TITLE, comics.getTitle());
            valores.put(DbOpenHelper.DESCRIPTION, comics.getDescription());
            valores.put(DbOpenHelper.PAGE_COUNT, comics.getPageCount());
            valores.put(DbOpenHelper.PRICE, comics.getPrice());
            valores.put(DbOpenHelper.THUMBNAIL, comics.getThumb());
            valores.put(DbOpenHelper.QTDE, quant);

            resultado = db.insert(DbOpenHelper.TABELA, null, valores);

        } else {

            String sql = " SELECT qtde FROM " + DbOpenHelper.TABELA + " WHERE " + DbOpenHelper.ID + " = " + comics.getId();

            Cursor cursor1 = db.rawQuery(sql, null);
            int valorCursor = cursor1.getInt(0);
            int soma = valorCursor + quant;

            String sqlUpdate = "UPDATE " + DbOpenHelper.TABELA + " SET COLUMN " + DbOpenHelper.QTDE + " = soma "
            +" WHERE " + DbOpenHelper.ID + " = " + comics.getId();

            db.execSQL(sqlUpdate);

        }

    }
    //select
    @Override
    public ArrayList<Item> carregarDados() {

        //getReadable database leitura de dados.
        SQLiteDatabase db = criarBanco.getReadableDatabase();
        ArrayList<Item> aux = new ArrayList<>();

        String sql = "SELECT * FROM " + DbOpenHelper.TABELA;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TITLE));
                int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.ID));
                int pageCount = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.PAGE_COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.PRICE));
                String desc = cursor.getString(cursor.getColumnIndex(DbOpenHelper.DESCRIPTION));
                String thumbnail = cursor.getString(cursor.getColumnIndex(DbOpenHelper.THUMBNAIL));
                int qtde = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.QTDE));
                aux.add(new Item(new Comics(id, title, desc, pageCount, price, thumbnail), qtde));

            } while (cursor.moveToNext());
        }

        db.close();
        return aux;
    }

    public boolean temId(int id) {

        Cursor rawQuery = db.rawQuery(
                "SELECT id from " + DbOpenHelper.TABELA + " WHERE id = ?", new String[]{String.valueOf(id)});
        int count = rawQuery.getCount();

        return count == 0;
    }


}