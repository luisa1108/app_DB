package com.tamayo.app_db;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public class AdaptadorParqueo extends ArrayAdapter<HashMap<String, String>> {

    private ArrayList<HashMap<String, String>> listaDeParqueos;

    public AdaptadorParqueo(Context context, ArrayList<HashMap<String, String>> listaDeParqueos) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, listaDeParqueos);
        this.listaDeParqueos = listaDeParqueos;
    }

    @NonNull
    @Override
    public View getView(int posicion, View convertView, @NonNull ViewGroup parent) {
        View vista = super.getView(posicion, convertView, parent);
        TextView texto1 = (TextView) vista.findViewById(android.R.id.text1);
        TextView texto2 = (TextView) vista.findViewById(android.R.id.text2);

        texto1.setText(listaDeParqueos.get(posicion).get("matricula"));
        texto2.setText(listaDeParqueos.get(posicion).get("tipo Vehiculo") + ", " + listaDeParqueos.get(posicion).get("color"));

        return vista;
    }
}
