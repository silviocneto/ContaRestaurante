package silvio.com.bar5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 2/17/2016.
 */
public class DBHelper_Pedidos extends SQLiteOpenHelper{
    private static final String NOME_BASE_PEDIDOS = "PEDIDOS";
    private static final int VERSAO_BASE_PEDIDOS = 1;

    public DBHelper_Pedidos(Context context){
        super(context, NOME_BASE_PEDIDOS, null, VERSAO_BASE_PEDIDOS);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTabelaPedidos = "CREATE TABLE Pedidos("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "iditem INTEGER"
                + ")";
        db.execSQL(sqlCreateTabelaPedidos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String sqlDropTabelaPedidos = "DROP TABLE Pedidos";
        db.execSQL(sqlDropTabelaPedidos);

        onCreate(db);
    }

    public void insertPedido(Item item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("iditem", item.getId());

        db.insert("Pedidos", null, cv);

        db.close();
    }

    public int getIdLastPedido(){   //método usado para obter o id do último pedido feito
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Pedidos", null);
        c.moveToLast();
        int idPedido = c.getInt(0);

        return idPedido;
    }

    public void DeletePedidos(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Pedidos", null, null);
        db.close();
    }

    /*public List<Item> selectTodosOsItens(){
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
    }*/
}
