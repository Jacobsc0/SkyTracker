package com.example.skytracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView listViewResenas;
    private ArrayAdapter<String> adapter;
    private ResenaOpenHelper baseDatosResena;
    private List<Resena> listaResenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listViewResenas = findViewById(R.id.listViewResenas);
        baseDatosResena = new ResenaOpenHelper(this);
        cargarResenas();

        listViewResenas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Resena resenaSeleccionada = listaResenas.get(position);
                mostrarDialogoOpciones(resenaSeleccionada);
            }
        });

        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresar a la actividad anterior
            }
        });
    }

    private void cargarResenas() {
        listaResenas = baseDatosResena.obtenerTodasLasResenas();
        List<String> textoResenas = new ArrayList<>();
        for (Resena resena : listaResenas) {
            textoResenas.add("ID: " + resena.getId() + "\n"
                    + "Correo: " + resena.getCorreo() + "\n"
                    + "Motivo: " + resena.getMotivo() + "\n"
                    + "Calificación: " + resena.getCalificacion() + "\n"
                    + "Descripción: " + resena.getDescripcion());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, textoResenas);
        listViewResenas.setAdapter(adapter);
    }

    private void mostrarDialogoOpciones(Resena resena) {
        String[] opciones = {"Editar", "Eliminar"};
        new AlertDialog.Builder(this)
                .setTitle("Seleccione una opción")
                .setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mostrarDialogoEditar(resena);
                        } else {
                            eliminarResena(resena);
                        }
                    }
                })
                .show();
    }

    private void mostrarDialogoEditar(Resena resena) {
        final EditText editCorreo = new EditText(this);
        editCorreo.setText(resena.getCorreo());
        editCorreo.setHint("Correo");

        final EditText editMotivo = new EditText(this);
        editMotivo.setText(resena.getMotivo());
        editMotivo.setHint("Motivo");

        final EditText editDescripcion = new EditText(this);
        editDescripcion.setText(resena.getDescripcion());
        editDescripcion.setHint("Descripción");

        final EditText editCalificacion = new EditText(this);
        editCalificacion.setText(String.valueOf(resena.getCalificacion()));
        editCalificacion.setHint("Calificación (1-5)");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editCorreo);
        layout.addView(editMotivo);
        layout.addView(editDescripcion);
        layout.addView(editCalificacion);

        new AlertDialog.Builder(this)
                .setTitle("Editar Reseña")
                .setView(layout)
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String correo = editCorreo.getText().toString();
                        String motivo = editMotivo.getText().toString();
                        String descripcion = editDescripcion.getText().toString();
                        int calificacion;

                        try {
                            calificacion = Integer.parseInt(editCalificacion.getText().toString());
                            if (calificacion < 1 || calificacion > 5) {
                                throw new NumberFormatException();
                            }
                            baseDatosResena.actualizarResena(resena.getId(), correo, motivo, calificacion, descripcion);
                            cargarResenas();
                            Toast.makeText(AdminActivity.this, "Reseña actualizada", Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(AdminActivity.this, "Calificación inválida", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void eliminarResena(Resena resena) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Reseña")
                .setMessage("¿Estás seguro de que quieres eliminar esta reseña?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseDatosResena.eliminarResena(resena.getId());
                        cargarResenas();
                        Toast.makeText(AdminActivity.this, "Reseña eliminada", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
