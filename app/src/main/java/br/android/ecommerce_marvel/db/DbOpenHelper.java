package br.android.ecommerce_marvel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;


public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String NAME_DATABASE = "comics.db";
    public static final String TABLE = "comics";
    public static final String _ID = "_id";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PAGE_COUNT = "page_count";
    public static final String PRICE = "price";
    public static final String QTY = "qty";
    public static final String THUMBNAIL = "thumbnail";
    public static final String RARE = "rare";
    private static final int VERSAO = 1;

    public DbOpenHelper(Context context) {
        super(context, NAME_DATABASE, null, VERSAO);
    }


    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        //CREATE TABLE IF NOT EXISTS
        String sql = "CREATE TABLE " + TABLE + "("
                + _ID + " INTEGER primary key autoincrement, "
                + ID + " INTEGER NOT NULL " + ","
                + TITLE + " TEXT " + ","
                + DESCRIPTION + " TEXT " + ","
                + PAGE_COUNT + " INTEGER " + ","
                + PRICE + " REAL " + ","
                + THUMBNAIL + " TEXT " + ","
                + QTY + " INTEGER " + ","
                + RARE + " BOOLEAN NOT NULL CHECK ( " + RARE + " IN (0,1)))";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);

    }
}
