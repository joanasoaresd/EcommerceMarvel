package br.android.ecommerce_marvel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

import static java.sql.Types.INTEGER;
import static java.sql.Types.REAL;
import static java.sql.Types.VARCHAR;


//RESPONSÁVEL PELA CRIAÇÃO DO BANCO E TAMBÉM RESPONSÁVEL PELO VERSIONAMENTO DO MESMO
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "comics.db";
    public static final String TABELA = "comics";
    public static final String _ID = "_id";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PAGE_COUNT = "page_count";
    public static final String PRICE = "price";
    public static final String QTDE = "qtde";
    public static final String THUMBNAIL = "thumbnail";
    public static final String RARO = "raro";
    private static final int VERSAO = 1;

    //private Context context;
    //private static DbOpenHelper instance;

    public DbOpenHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    // quando cria o bd pela primeira vez
    @Override
    public void onCreate(@NotNull SQLiteDatabase db) {
        //CREATE TABLE IF NOT EXISTS
        String sql = "CREATE TABLE " + TABELA + "("
                + _ID + " INTEGER primary key autoincrement, "
                + ID + " INTEGER NOT NULL " + ","
                + TITLE + " TEXT " + ","
                + DESCRIPTION + " TEXT " + ","
                + PAGE_COUNT + " INTEGER " + ","
                + PRICE + " REAL " + ","
                + THUMBNAIL + " TEXT " + ","
                + QTDE + " INTEGER " + ","
                + RARO + " BOOLEAN NOT NULL CHECK ( " + RARO + " IN (0,1)))";

        db.execSQL(sql);
    }

    //atualizar o bd com alguma informação
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }
}
