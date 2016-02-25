package silvio.com.bar5;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Silvio on 2/17/2016.
 */
public class DBHelper_PedidosParticipantes extends SQLiteOpenHelper{
    private static final String NOME_BASE_PP = "PEDIDOS_PARTICIPANTES";
    private static final int VERSAO_BASE_PP = 1;

    public DBHelper_PedidosParticipantes(Context context){
        super(context, NOME_BASE_PP, null, VERSAO_BASE_PP);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTabelaPP = "CREATE TABLE PedidosParticipantes("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "idpedido INTEGER,"
                + "idparticip INTEGER"
                + ")";
        db.execSQL(sqlCreateTabelaPP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String sqlDropTabelaPP = "DROP TABLE PedidosParticipantes";
        db.execSQL(sqlDropTabelaPP);

        onCreate(db);
    }

    public void insertPP(int id_Pedido, Participante participante){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("idpedido", id_Pedido);
        cv.put("idparticip", participante.getId());

        db.insert("PedidosParticipantes", null, cv);

        db.close();
    }

    public void DeletePP(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("PedidosParticipantes", null, null);
        db.close();
    }

    /*public List<Objeto_conta> CalculaConta(){
        try {
            SQLiteDatabase db = getReadableDatabase();
            //Cursor c = db.rawQuery("SELECT * FROM PedidosParticipantes", null);
            Cursor c = db.rawQuery("SELECT * FROM PedidosParticipantes " +
                    "INNER JOIN Participantes " +
                    "ON (PedidosParticipantes.idparticip = Participantes.id)", null); //TA DANDO ERRO!!
            List<Objeto_conta> Conta = new ArrayList<Objeto_conta>();
            if (c.moveToFirst()) {
                do {
                    Objeto_conta objeto = new Objeto_conta();
                    objeto.setIdpedido(c.getInt(1));
                    objeto.setIntegName(c.getString(4));
                    Conta.add(objeto);
                    //String preco = Double.toString(c.getDouble(1));
                    //Log.e("CONTA", idparticip + "   " + preco);
                } while (c.moveToNext());
            }
            return Conta;
        } catch (Exception e){
                Log.i("ERRO","ERROU" + e);
                return null;
        }
    }*/

    /*public List<Participante> selectTodosOsParticipantes(){
        List<Participante> listParticipantes = new ArrayList<Participante>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelectTodosParticipantes = "SELECT * FROM Participantes";

        Cursor c = db.rawQuery(sqlSelectTodosParticipantes, null);

        if(c.moveToFirst()) {
            do {
                Participante participante = new Participante();
                participante.setId(c.getInt(0));
                participante.setName(c.getString(1));

                listParticipantes.add(participante);
            } while (c.moveToNext());
        }


        return listParticipantes;
    }*/
}
