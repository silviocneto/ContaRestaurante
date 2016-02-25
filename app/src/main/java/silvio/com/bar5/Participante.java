package silvio.com.bar5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Silvio on 1/27/2016.
 */
public class Participante {
    private int id;
    private String name;
    private boolean selected;

    public Participante(){}

    public Participante(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSelected(boolean isChecked){
        this.selected = isChecked;
    }

    public boolean isSelected(){
        return selected;
    }


    @Override
    public String toString() {
        /*return "Item{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
                */
        return name;

    }

}
