package com.tamayo.app_db;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.annotation.NonNull;

public class DatabaseManager {

    private ArrayList<HashMap<String, String>> listaDeParqueos = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getListaDeParqueos() {
        return listaDeParqueos;
    }

    public void cargarParqueosDeDB(final AdaptadorParqueo adaptadorParqueos) {

        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaParqueos = baseDeDatos.getReference("Parqueo");

        referenciaParqueos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaDeParqueos.clear();
                for (DataSnapshot parqueoSnapshot: dataSnapshot.getChildren()) {
                    HashMap<String, String> parqueo = (HashMap<String, String>) parqueoSnapshot.getValue();
                    listaDeParqueos.add(parqueo);
                }
                adaptadorParqueos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError errorBaseDeDatos) {
                //handle databaseError
            }
        });
    }
}
