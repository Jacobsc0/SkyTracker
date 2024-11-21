package com.example.skytracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView listViewResenas;
    private ArrayAdapter<String> adapter;
    private List<String> listaResenasTexto;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        listViewResenas = findViewById(R.id.listViewResenas);
        listaResenasTexto = new ArrayList<>();

        // Configurar Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("resenas");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaResenasTexto);
        listViewResenas.setAdapter(adapter);

        cargarResenas();
    }

    private void cargarResenas() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listaResenasTexto.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Resena resena = data.getValue(Resena.class);
                    if (resena != null) {
                        String textoResena = "Correo: " + resena.getCorreo() + "\n"
                                + "Motivo: " + resena.getMotivo() + "\n"
                                + "Calificación: " + resena.getCalificacion() + "\n"
                                + "Descripción: " + resena.getDescripcion();
                        listaResenasTexto.add(textoResena);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(AdminActivity.this, "Error al cargar las reseñas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
