package silvio.com.bar5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Silvio on 1/24/2016.
 */
public class MostraCardapioActivity extends Activity{

    ListView ListCardapio;
    //Button AbreCadastroButton;

    //ListView ListCardapio = (ListView) findViewById(R.id.listacardapio);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_cardapio);

        Button AbreCadastroButton = (Button) findViewById(R.id.abrecadastro_cardapio);
        ListCardapio = (ListView) findViewById(R.id.listacardapio);

        AbreCadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MostraCardapioActivity.this, CadastroCardapioActivity.class);
                //Intent it = new Intent(getApplicationContext(), CadastroCardapioActivity.class);
                startActivity(it);

            }
        });

        ListCardapio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicou", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() { //Sempre que voltar pra essa activity ele roda essa parte
        super.onResume();

        DBHelper dbHelper = new DBHelper(this);
        List<Item> listItens = dbHelper.selectTodosOsItens(); //Esse método retorna uma lista, logo tem que ser igual a um tipo de lista

        //Para passar a lista pro ListView é preciso adaptar ela usando esse método a seguir
        ArrayAdapter<Item> adaptador = new ArrayAdapter<Item>(this, android.R.layout.simple_expandable_list_item_1, listItens);

        ListCardapio.setAdapter(adaptador);

    }
}

