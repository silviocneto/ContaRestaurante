package silvio.com.bar5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 1/23/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_BASE_CARDAPIO = "CARDAPIO";
    private static final int VERSAO_BASE_CARDAPIO = 1;

    public DBHelper(Context context){
        super(context, NOME_BASE_CARDAPIO, null, VERSAO_BASE_CARDAPIO);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTabelaCardapio = "CREATE TABLE Cardapio("
                                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                        + "nome TEXT,"
                                        + "preco VARCHAR"
                                        + ")";
        db.execSQL(sqlCreateTabelaCardapio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String sqlDropTabelaCardapio = "DROP TABLE Cardapio";
        db.execSQL(sqlDropTabelaCardapio);

        onCreate(db);
    }

    public void insertItem(Item item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nome", item.getNome());
        cv.put("preco", item.getPreco());

        db.insert("Cardapio", null, cv);

        db.close();
    }

    public List<Item> selectTodosOsItens(){
        List<Item> listItens = new ArrayList<Item>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelectTodosItens = "SELECT * FROM Cardapio";

        Cursor c = db.rawQuery(sqlSelectTodosItens, null);

        if(c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(c.getInt(0));
                item.setNome(c.getString(1));
                item.setPreco(c.getDouble(2));

                listItens.add(item);
            } while (c.moveToNext());
        }

        return listItens;
    }

    public void DeleteCardapio(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Cardapio", null, null);
        db.close();
    }
    //Eu quero que o usuario clique num botao Delete
    //O botao o leve para uma nova activity com a lista dos itens presentes
    //Quero que ele selecione o item e ele seja deletado
    //Em seguida volta para a activity antiga
    /*public void deleteContact(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        new String
        //db.delete("Cardapio", "id" + " = ?", new String[] { String.valueOf(item.getId()) });
        db.delete
        db.close();
    }*/
}
