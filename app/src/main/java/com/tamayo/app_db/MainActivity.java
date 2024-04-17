package com.tamayo.app_db;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView listaParqueos;
    private FloatingActionButton botonFlotante;
    private AdaptadorParqueo adaptadorParqueos;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaParqueos = (ListView) findViewById(R.id.listaParqueos);
        botonFlotante = (FloatingActionButton) findViewById(R.id.botonFlotante);

        databaseManager = new DatabaseManager();
        adaptadorParqueos = new AdaptadorParqueo(this, databaseManager.getListaDeParqueos());

        listaParqueos.setAdapter(adaptadorParqueos);

        databaseManager.cargarParqueosDeDB(adaptadorParqueos);

        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, AddQuoteActivity.class);
                startActivity(intento);
            }
        });
    }
}