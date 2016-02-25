package silvio.com.bar5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Silvio on 1/24/2016.
 */
public class CadastroCardapioActivity extends Activity {

    EditText nome_item_EditText;
    EditText preco_item_EditText;
    Button add_item_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_cardapio);

        nome_item_EditText = (EditText)findViewById(R.id.nome_item_EditText);
        preco_item_EditText = (EditText)findViewById(R.id.preco_item_EditText);
        add_item_button = (Button)findViewById(R.id.cadastra_item_button);

        final Item item = new Item();
        final DBHelper dbHelper = new DBHelper(this);

        add_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setNome(nome_item_EditText.getText().toString());

                String preco_string = preco_item_EditText.getText().toString();
                Double preco_item = Double.parseDouble(preco_string);   //O valor inserido tem que ser com ponto e não com vírgula
                item.setPreco(preco_item);

                dbHelper.insertItem(item);

                nome_item_EditText.setText("");
                preco_item_EditText.setText("");

                Intent int3 = new Intent(CadastroCardapioActivity.this, MostraCardapioActivity.class);
                CadastroCardapioActivity.this.startActivity(int3);

            }
        });



    }
}
