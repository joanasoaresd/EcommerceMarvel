package br.android.ecommerce_marvel.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import br.android.ecommerce_marvel.model.Comics;
import br.android.ecommerce_marvel.model.Item;

//CONTÉM CONTEÚDOS DE MANIPULAÇÃO DOS DADOS NO BANCO
public class DbDatabaseComic implements DAO {

    private SQLiteDatabase db;
    private DbOpenHelper criarBanco;

    public DbDatabaseComic(Context context) {
        criarBanco = new DbOpenHelper(context);
    }

    //inserir dados no banco
    @Override
    public void inserirDados(Comics comics, int quant) {

        ContentValues valores = new ContentValues();
        //getWritableDatabase p/ leitura e escrita de dados
        db = criarBanco.getWritableDatabase();

            valores.put(DbOpenHelper.ID, comics.getId());
            valores.put(DbOpenHelper.TITLE, comics.getTitle());
            valores.put(DbOpenHelper.DESCRIPTION, comics.getDescription());
            valores.put(DbOpenHelper.PAGE_COUNT, comics.getPageCount());
            valores.put(DbOpenHelper.PRICE, comics.getPrice());
            valores.put(DbOpenHelper.THUMBNAIL, comics.getThumb());
            valores.put(DbOpenHelper.QTDE, quant);

            db.insert(DbOpenHelper.TABELA, null, valores);

    }

    //define quantidade com soma
    public void atualizarQTDE(Comics c, int qtde) {
        db = criarBanco.getWritableDatabase();
        if (nTemId(c.getId())) {

            inserirDados(c, qtde);

        } else {

            String sql = " SELECT qtde FROM " + DbOpenHelper.TABELA + " WHERE " + DbOpenHelper.ID + " = " + c.getId();
            Cursor cursor1 = db.rawQuery(sql, null);

            if (cursor1.moveToFirst()) {

                int valorCursor = cursor1.getInt(0);
                int soma = valorCursor + qtde;

                String sqlUpdate = "UPDATE " + DbOpenHelper.TABELA + " SET " + DbOpenHelper.QTDE + " = " + soma
                        + " WHERE " + DbOpenHelper.ID + " = " + c.getId();

                db.execSQL(sqlUpdate);

            }
        }
    }
        //select
        @Override
        public ArrayList<Item> carregarDados () {

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

        //select onde id
        public boolean nTemId (int id){

            Cursor rawQuery = db.rawQuery("SELECT id FROM " + DbOpenHelper.TABELA + " WHERE id = ?", new String[]{String.valueOf(id)});
            int count = rawQuery.getCount();

            return count == 0;
        }

        //deletar registros especificos
        public void deletarRegistros (int id) {
            db = criarBanco.getReadableDatabase();
            db.delete(DbOpenHelper.TABELA, "id=?", new String[]{id + ""});
            db.close();

    }
        //deletar ao finallizar compras
        public void deletarTodosRegistros(){
                db = criarBanco.getWritableDatabase();
                db.execSQL("DELETE FROM " + DbOpenHelper.TABELA);
                db.close();

        }

        //atualizar quantidade digitada
        public void atualizarLista(Item i, int qtde) {
            db = criarBanco.getReadableDatabase();
            String sqlUpdate = "UPDATE " + DbOpenHelper.TABELA + " SET " + DbOpenHelper.QTDE + " = " + qtde
                    + " WHERE " + DbOpenHelper.ID + " = " + i.getComics().getId();

            db.execSQL(sqlUpdate);
            }

        }

