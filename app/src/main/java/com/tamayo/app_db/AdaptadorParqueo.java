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

    private ArrayList<HashMap<String, String>> listaParqueos;

    public AdaptadorParqueo(Context context, ArrayList<HashMap<String, String>> listaParqueos) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, listaParqueos);
        this.listaParqueos = listaParqueos;
    }

    @NonNull
    @Override
    public View getView(int posicion, View convertView, @NonNull ViewGroup parent) {
        View vista = super.getView(posicion, convertView, parent);
        TextView texto1 = (TextView) vista.findViewById(android.R.id.text1);
        TextView texto2 = (TextView) vista.findViewById(android.R.id.text2);

        texto1.setText(listaParqueos.get(posicion).get("matricula"));
        texto2.setText(listaParqueos.get(posicion).get("tipo_vehiculo") + ", " + listaParqueos.get(posicion).get("categoria"));

        return vista;
    }
}
