package silvio.com.bar5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silvio on 1/27/2016.
 */
public class DBHelper_P extends SQLiteOpenHelper {
    private static final String NOME_BASE_Participantes = "PARTICIPANTES";
    private static final int VERSAO_BASE_Participantes = 2;

    public DBHelper_P(Context context){
        super(context, NOME_BASE_Participantes, null, VERSAO_BASE_Participantes);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlCreateTabelaParticipantes = "CREATE TABLE Participantes("
                + "id_participante INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name_participante TEXT"
                + ")";
        db.execSQL(sqlCreateTabelaParticipantes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        String sqlDropTabelaParticipantes = "DROP TABLE Participantes";
        db.execSQL(sqlDropTabelaParticipantes);

        onCreate(db);
    }

    public void insertParticipante(Participante participante){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv2 = new ContentValues();

        cv2.put("name_participante", participante.getName());

        db.insert("Participantes", null, cv2);

        db.close();
    }

    public List<Participante> selectTodosOsParticipantes(){
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
    }

    public void DeleteIntegrantes(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Participantes", null, null);
        db.close();
    }
}
