package silvio.com.bar5;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


/**
 * Created by Silvio on 2/3/2016.
 */
public class MenuPrincipalActivity extends Activity {

    //Button GoCardapio, GoIntegrantes, GoPedidos, GoConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        //Chama as Databases para posteriormente deletar a tabela antiga registrada
        //final DBHelper db = new DBHelper(this);
        final DBHelper_P db_P = new DBHelper_P(this);
        final DBHelper_Pedidos db_pedidos = new DBHelper_Pedidos(this);
        final DBHelper_PedidosParticipantes db_PP = new DBHelper_PedidosParticipantes(this);


        Button GoCardapio = (Button) findViewById(R.id.gocardapio_button);
        Button GoIntegrantes = (Button) findViewById(R.id.gointegrantes_button);
        Button GoPedidos = (Button) findViewById(R.id.gopedidos_button);
        Button GoConta = (Button) findViewById(R.id.goconta_button);
        Button DeleteAll = (Button) findViewById(R.id.delete_button);

        GoCardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipalActivity.this, MostraCardapioActivity.class);
                startActivity(it);
            }
        });

        GoIntegrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(MenuPrincipalActivity.this, MostraParticipantesActivity.class);
                startActivity(it2);
            }
        });

        GoPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it3 = new Intent(MenuPrincipalActivity.this, PedidoActivity.class);
                startActivity(it3);
            }
        });

        GoConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it4 = new Intent(MenuPrincipalActivity.this, ContaActivity.class);
                startActivity(it4);
            }
        });

        DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deletando as tabelas antigas de outros usos
                //db.DeleteCardapio();
                db_P.DeleteIntegrantes();
                db_pedidos.DeletePedidos();
                db_PP.DeletePP();
            }
        });
    }

}
