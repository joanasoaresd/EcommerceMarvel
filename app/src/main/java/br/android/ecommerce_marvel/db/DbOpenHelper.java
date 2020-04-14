package br.android.ecommerce_marvel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//RESPONSÁVEL PELA CRIAÇÃO DO BANCO E TAMBÉM RESPONSÁVEL PELO VERSIONAMENTO DO MESMO
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "comics.db";
    public static final String TABELA = "comics";
    public static final String _ID = "_id";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final int PAGE_COUNT = 10;
    public static final double PRICE = 0.00;
    public static final String THUMBNAIL = "thumbnail";
    private static final int VERSAO = 1;



    public DbOpenHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    // quando cria o bd pela primeira vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE IF NOT EXISTS
        String sql = "CREATE TABLE " + TABELA + "("
                + _ID + "integer primary key autoincrement,"
                + TITLE + "text,"
                + DESCRIPTION + "text,"
                + PAGE_COUNT + "integer,"
                + PRICE + "real,"
                + THUMBNAIL + "thumbnail" + ")";

        db.execSQL(sql);
    }

    //atualizar o bd com alguma informação
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }
}
