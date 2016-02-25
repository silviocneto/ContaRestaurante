package silvio.com.bar5;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 2/3/2016.
 */
public class PedidoActivity extends Activity {

    Spinner Listaitens;
    ArrayAdapter<Item> adapter;
    MyCustomAdapter dataAdapter;
    public Item selecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazer_pedido);

        Listaitens = (Spinner)findViewById(R.id.spinner_itens);
        TextView Escolher_pedido = (TextView)findViewById(R.id.escolher_pedido_TextView);

        DBHelper dbHelper_pedido = new DBHelper(this);
        List<Item> listItens_pedido = dbHelper_pedido.selectTodosOsItens();

        //createFromResource(context,arraylist,tipo de layout do spinner);
        //adapter = ArrayAdapter.createFromResource(this,R.array.nome_itens,android.R.layout.simple_spinner_item);
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_spinner_item, listItens_pedido);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Listaitens.setAdapter(adapter);
        Listaitens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Pegando o item selecionado para (no OrderButtonClick) inserir a id dele na tabela Pedidos (ao clicar no botao FAZER PEDIDO)
                selecteditem = (Item) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Selecionou", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-------------Até aqui é o Sinner-------
        displayListView();
        OrderButtonClick();

    }

    private void displayListView() {
        //Array de participantes
        DBHelper_P DB_Participantes = new DBHelper_P(this);
        List<Participante> participList = DB_Participantes.selectTodosOsParticipantes(); // Converti a List que a DB cospe em uma ArrayList que
        ArrayList<Participante> participArrayList = new ArrayList<Participante>(participList); // o MyCustomAdapter usa

        //create an ArrayAdapter from the String Array
        dataAdapter = new MyCustomAdapter(this, R.layout.fazer_pedido_checkbox, participArrayList);
        ListView participListView = (ListView) findViewById(R.id.quem_consome_listview);
        // Assign adapter to ListView
        participListView.setAdapter(dataAdapter);

        participListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                Participante integrante = (Participante) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Clicou em" + integrante.getName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Participante> {

        private ArrayList<Participante> participList2;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Participante> participList) { //Método contrutor da classe - Possui o mesmo nome da classe
            super(context, textViewResourceId, participList);
            this.participList2 = new ArrayList<Participante>();
            this.participList2.addAll(participList);
        }

        private class ViewHolder {
            //TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.fazer_pedido_checkbox, null);

                holder = new ViewHolder();
                //holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Participante participante = (Participante) cb.getTag();
                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + cb.getText(), Toast.LENGTH_LONG).show();
                        participante.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Participante participante = participList2.get(position);
            //holder.code.setText(" (" +  country.getCode() + ")");
            holder.name.setText(participante.getName());
            holder.name.setChecked(participante.isSelected());
            holder.name.setTag(participante);

            return convertView;

        }
    }

    private void OrderButtonClick(){
        Button pedido_button = (Button) findViewById(R.id.salvarpedido_button);
        final DBHelper_Pedidos db_pedidos = new DBHelper_Pedidos(this);
        final DBHelper_PedidosParticipantes db_PP = new DBHelper_PedidosParticipantes(this);

        pedido_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inserindo o id do selecteditem na tabela  Pedidos
                db_pedidos.insertPedido(selecteditem);

                //Inserindo o id do pedido recem adicionado (aqui em cima) e os id dos integrantes
                ArrayList<Participante> ArrayList = dataAdapter.participList2;
                for(int i=0;i<ArrayList.size();i++){
                    Participante particip_add = ArrayList.get(i);
                    if(particip_add.isSelected()){
                        int IdLastPedido = db_pedidos.getIdLastPedido();
                        db_PP.insertPP(IdLastPedido,particip_add);

                        String idPedido = Integer.toString(IdLastPedido);
                        String idInteg = Integer.toString(particip_add.getId());
                        Log.e("TAG",idPedido + "  " + idInteg);

                    }
                }
                Toast.makeText(getApplicationContext(), "Deu Certo!", Toast.LENGTH_LONG).show();
            }
        });
    }
    /*private void checkButtonClick() {

        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Country> countryList = dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    Country country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }*/
}
