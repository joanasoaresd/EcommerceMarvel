package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.android.ecommerce_marvel.model.ComicsDTO;
import br.android.ecommerce_marvel.model.Item;
import br.android.ecommerce_marvel.utils.LoggerUtils;


public class DbDatabaseComic implements IDAO {

    private SQLiteDatabase read;
    private SQLiteDatabase write;
    private static DbOpenHelper createDatabase;
    private static DbDatabaseComic instance;
    private static final String TAG = "DATABASE";

    private DbDatabaseComic(Context context) {
        createDatabase = new DbOpenHelper(context);
        this.read = createDatabase.getReadableDatabase();
        this.write = createDatabase.getWritableDatabase();
        LoggerUtils.log(TAG, "Instância");
    }

    public static DbDatabaseComic getInstance(Context context) {
        if (instance == null) {
            instance = new DbDatabaseComic(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void insertData(ComicsDTO comics, int qty) {

        ContentValues valores = new ContentValues();

        valores.put(DbOpenHelper.ID, comics.getId());
        valores.put(DbOpenHelper.TITLE, comics.getTitle());
        valores.put(DbOpenHelper.DESCRIPTION, comics.getDescription());
        valores.put(DbOpenHelper.PAGE_COUNT, comics.getPageCount());
        valores.put(DbOpenHelper.PRICE, comics.getPrice());
        valores.put(DbOpenHelper.THUMBNAIL, comics.getThumb());
        valores.put(DbOpenHelper.QTY, qty);
        valores.put(DbOpenHelper.RARE, comics.getRare());

        write.insert(DbOpenHelper.TABLE, null, valores);

    }

    public void updateQty(ComicsDTO c, int qty) {
        if (hasNoId(c.getId())) {

            insertData(c, qty);

        } else {

            String sql = " SELECT qtde FROM " + DbOpenHelper.TABLE + " WHERE " + DbOpenHelper.ID + " = " + c.getId();
            Cursor cursor1 = write.rawQuery(sql, null);

            if (cursor1.moveToFirst()) {

                int valorCursor = cursor1.getInt(0);
                int soma = valorCursor + qty;

                String sqlUpdate = "UPDATE " + DbOpenHelper.TABLE + " SET " + DbOpenHelper.QTY + " = " + soma
                        + " WHERE " + DbOpenHelper.ID + " = " + c.getId();

                write.execSQL(sqlUpdate);

            }
        }
    }

    @Override
    public ArrayList<Item> loadData() {
        ArrayList<Item> aux = new ArrayList<>();

        String sql = "SELECT * FROM " + DbOpenHelper.TABLE;

        Cursor cursor = read.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TITLE));
                int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.ID));
                int pageCount = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.PAGE_COUNT));
                double price = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.PRICE));
                String desc = cursor.getString(cursor.getColumnIndex(DbOpenHelper.DESCRIPTION));
                String thumbnail = cursor.getString(cursor.getColumnIndex(DbOpenHelper.THUMBNAIL));
                boolean rare = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.RARE)) > 0;
                int qty = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.QTY));
                aux.add(new Item(new ComicsDTO(id, title, desc, pageCount, price, thumbnail, rare), qty));

            } while (cursor.moveToNext());
        }

        return aux;
    }

    public boolean hasNoId(int id) {

        Cursor rawQuery = read.rawQuery("SELECT id FROM " + DbOpenHelper.TABLE + " WHERE id = ?", new String[]{String.valueOf(id)});
        int count = rawQuery.getCount();

        return count == 0;
    }

    public void deleteRecords(int id) {
        read.delete(DbOpenHelper.TABLE, "id=?", new String[]{id + ""});
    }


    public void deleteAllRecords() {
        write.execSQL("DELETE FROM " + DbOpenHelper.TABLE);
    }


    public void updateList(Item i, int qty) {

        String sqlUpdate = "UPDATE " + DbOpenHelper.TABLE + " SET " + DbOpenHelper.QTY + " = " + qty
                + " WHERE " + DbOpenHelper.ID + " = " + i.getComics().getId();

        read.execSQL(sqlUpdate);
    }

}

