package silvio.com.bar5;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 2/23/2016.
 */
public class ContaActivity extends Activity {

    ListView ListConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_conta);
        ListConta = (ListView)findViewById(R.id.conta_listview);
    }

    @Override
    protected void onResume() { //Sempre que voltar pra essa activity ele roda essa parte
        super.onResume();

        try{
            DBHelper_PedidosParticipantes db_PP = new DBHelper_PedidosParticipantes(this);
            DBHelper_P db_P = new DBHelper_P(this);
            db_P.getReadableDatabase();
            //---------------------------------------Primeira tentativa--------------------------------------
            //String tabela = db_P.getReadableDatabase().findEditTable("Participantes, CASDAS, Pedidos");
            //Log.e("TABELA", tabela);
            Cursor c = db_PP.getReadableDatabase().rawQuery("SELECT * FROM PedidosParticipantes " +
                    "INNER JOIN Participantes " +
                    "ON (PedidosParticipantes.idparticip = Participantes.id_participante)", null);
            //-------------------------------------Segunda tentativa ----------------------------------------
            /*SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables("PedidosParticipantes " +
                    "INNER JOIN Participantes" +
                   "ON PedidosParticipantes.idparticip = Participantes.id_participante");
            Cursor c = queryBuilder.query(db_PP.getReadableDatabase(),null,null, null, null, null, null);*/
            //----------------------------------------------------------------------------------------------
            List<Objeto_conta> Conta = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    Objeto_conta objeto = new Objeto_conta();
                    objeto.setIdpedido(c.getInt(1));
                    objeto.setIntegName(c.getString(4));
                    Conta.add(objeto);
                    //String preco = Double.toString(c.getDouble(1));
                    //Log.e("CONTA", idparticip + "   " + preco);
                }while (c.moveToNext());
            }

        //List<Objeto_conta> Conta = db_PP.CalculaConta(); //Esse método retorna uma lista, logo tem que ser igual a um tipo de lista
        //Para passar a lista pro ListView é preciso adaptar ela usando esse método a seguir
        ArrayAdapter<Objeto_conta> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, Conta);
        ListConta.setAdapter(adapter);

        } catch (Exception e){
            Log.i("ERRO", "ERROU" + e);
        }
    }
}

    /*public List<Objeto_conta> CalculaConta(){
        try {
            //final DBHelper_PedidosParticipantes db_PP = new DBHelper_PedidosParticipantes(this);
            final DBHelper_P db_P = new DBHelper_P(this);
            SQLiteDatabase db_PP = getReadableDatabase();
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
            Log.i("ERRO", "ERROU" + e);
            return null;
        }
    }*/
