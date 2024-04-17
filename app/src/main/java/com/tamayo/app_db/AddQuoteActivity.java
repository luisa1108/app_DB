package com.tamayo.app_db;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuoteActivity extends AppCompatActivity {

    private EditText editTextMatricula;
    private EditText editTextTipoVehiculo;
    private EditText editTextColor;
    private Button botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        editTextMatricula = (EditText) findViewById(R.id.editTextMatricula);
        editTextTipoVehiculo = (EditText) findViewById(R.id.editTextTipoVehiculo);
        editTextColor = (EditText) findViewById(R.id.editTextColor);
        botonAgregar = (Button) findViewById(R.id.botonAgregar);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text
                String matricula = editTextMatricula.getText().toString();
                String tipo_vehiculo = editTextTipoVehiculo.getText().toString();
                String color = editTextColor.getText().toString();

                if (matricula.isEmpty()){
                    editTextMatricula.setError("No puede estar vacío");
                    return;
                }
                if (tipo_vehiculo.isEmpty()){
                    editTextTipoVehiculo.setError("No puede estar vacío");
                    return;
                }
                if (color.isEmpty()){
                    editTextColor.setError("No puede estar vacío");
                    return;
                }

                agregarParqueoABD(matricula, tipo_vehiculo, color);
            }
        });
    }

    private void agregarParqueoABD(String matricula, String tipo_vehiculo, String color) {
        //create a hashmap
        HashMap<String, Object> parqueoHashmap = new HashMap<>();
        parqueoHashmap.put("matricula", matricula);
        parqueoHashmap.put("tipo Vehiculo", tipo_vehiculo);
        parqueoHashmap.put("color", color);

        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaParqueos = baseDeDatos.getReference("Parqueos");

        String clave = referenciaParqueos.push().getKey();
        parqueoHashmap.put("clave", clave);

        referenciaParqueos.child(clave).setValue(parqueoHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuoteActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                editTextMatricula.getText().clear();
                editTextTipoVehiculo.getText().clear();
                editTextColor.getText().clear();
            }
        });
    }
}
