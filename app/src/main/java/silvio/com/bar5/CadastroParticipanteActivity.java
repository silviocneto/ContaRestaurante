package silvio.com.bar5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Silvio on 1/27/2016.
 */
public class CadastroParticipanteActivity extends Activity{

    EditText name_EditText;
    Button add_name_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_participante);

        name_EditText = (EditText) findViewById(R.id.name_EditText);
        add_name_button = (Button) findViewById(R.id.cadastra_name_button);

        final Participante participante = new Participante();
        final DBHelper_P dbHelper2 = new DBHelper_P(this);

        add_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                participante.setName(name_EditText.getText().toString());

                dbHelper2.insertParticipante(participante);

                name_EditText.setText("");

                Intent int3 = new Intent(CadastroParticipanteActivity.this, MostraParticipantesActivity.class);
                CadastroParticipanteActivity.this.startActivity(int3);
            }
        });
    }
}
