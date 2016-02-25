package silvio.com.bar5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Silvio on 1/27/2016.
 */
public class MostraParticipantesActivity extends Activity {

    ListView ListParticipantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_participantes);

        Button AbreCadastroButton_P = (Button) findViewById(R.id.abrecadastro_participantes);
        ListParticipantes = (ListView) findViewById(R.id.listaparticipantes);

        AbreCadastroButton_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(MostraParticipantesActivity.this, CadastroParticipanteActivity.class);
                //Intent it = new Intent(getApplicationContext(), CadastroCardapioActivity.class);
                startActivity(it2);
            }
        });
    }

    @Override
    protected void onResume() { //Sempre que voltar pra essa activity ele roda essa parte
        super.onResume();

        DBHelper_P dbHelper2 = new DBHelper_P(this);
        List<Participante> listParticipantes = dbHelper2.selectTodosOsParticipantes(); //Esse método retorna uma lista, logo tem que ser igual a um tipo de lista

        //Para passar a lista pro ListView é preciso adaptar ela usando esse método a seguir
        ArrayAdapter<Participante> adaptador2 = new ArrayAdapter<Participante>(this, android.R.layout.simple_expandable_list_item_1, listParticipantes);

        ListParticipantes.setAdapter(adaptador2);

    }
}
