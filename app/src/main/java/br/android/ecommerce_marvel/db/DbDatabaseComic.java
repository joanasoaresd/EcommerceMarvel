package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;


public class DbDatabaseComic implements DAO {

    private SQLiteDatabase read;
    private SQLiteDatabase write;
    private static DbOpenHelper criarBanco;
    private static DbDatabaseComic instance;

    private DbDatabaseComic(Context context) {
        criarBanco = new DbOpenHelper(context);
        this.read = criarBanco.getReadableDatabase();
        this.write = criarBanco.getWritableDatabase();
        System.out.println("Instanciaa");
    }

    public static DbDatabaseComic getInstance(Context context) {
        if (instance == null) {
            instance = new DbDatabaseComic(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void inserirDados(Comics comics, int quant) {

        ContentValues valores = new ContentValues();

        valores.put(DbOpenHelper.ID, comics.getId());
        valores.put(DbOpenHelper.TITLE, comics.getTitle());
        valores.put(DbOpenHelper.DESCRIPTION, comics.getDescription());
        valores.put(DbOpenHelper.PAGE_COUNT, comics.getPageCount());
        valores.put(DbOpenHelper.PRICE, comics.getPrice());
        valores.put(DbOpenHelper.THUMBNAIL, comics.getThumb());
        valores.put(DbOpenHelper.QTDE, quant);
        valores.put(DbOpenHelper.RARO, comics.getRaro());

        write.insert(DbOpenHelper.TABELA, null, valores);

    }

    public void atualizarQTDE(Comics c, int qtde) {
        if (nTemId(c.getId())) {

            inserirDados(c, qtde);

        } else {

            String sql = " SELECT qtde FROM " + DbOpenHelper.TABELA + " WHERE " + DbOpenHelper.ID + " = " + c.getId();
            Cursor cursor1 = write.rawQuery(sql, null);

            if (cursor1.moveToFirst()) {

                int valorCursor = cursor1.getInt(0);
                int soma = valorCursor + qtde;

                String sqlUpdate = "UPDATE " + DbOpenHelper.TABELA + " SET " + DbOpenHelper.QTDE + " = " + soma
                        + " WHERE " + DbOpenHelper.ID + " = " + c.getId();

                write.execSQL(sqlUpdate);

            }
        }
    }

    @Override
    public ArrayList<Item> carregarDados() {
        ArrayList<Item> aux = new ArrayList<>();

        String sql = "SELECT * FROM " + DbOpenHelper.TABELA;

        Cursor cursor = read.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TITLE));
                int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.ID));
                int pageCount = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.PAGE_COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.PRICE));
                String desc = cursor.getString(cursor.getColumnIndex(DbOpenHelper.DESCRIPTION));
                String thumbnail = cursor.getString(cursor.getColumnIndex(DbOpenHelper.THUMBNAIL));
                boolean rare = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.RARO)) > 0;
                int qtde = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.QTDE));
                aux.add(new Item(new Comics(id, title, desc, pageCount, price, thumbnail, rare), qtde));

            } while (cursor.moveToNext());
        }

        return aux;
    }

    public boolean nTemId(int id) {

        Cursor rawQuery = read.rawQuery("SELECT id FROM " + DbOpenHelper.TABELA + " WHERE id = ?", new String[]{String.valueOf(id)});
        int count = rawQuery.getCount();

        return count == 0;
    }

    public void deletarRegistros(int id) {
        read.delete(DbOpenHelper.TABELA, "id=?", new String[]{id + ""});
    }


    public void deletarTodosRegistros() {
        write.execSQL("DELETE FROM " + DbOpenHelper.TABELA);
    }


    public void atualizarLista(Item i, int qtde) {

        String sqlUpdate = "UPDATE " + DbOpenHelper.TABELA + " SET " + DbOpenHelper.QTDE + " = " + qtde
                + " WHERE " + DbOpenHelper.ID + " = " + i.getComics().getId();

        read.execSQL(sqlUpdate);
    }

}

