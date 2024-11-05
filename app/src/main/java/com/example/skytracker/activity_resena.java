package com.example.skytracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_resena extends AppCompatActivity {

    private Spinner spinnerMotivo;
    private RatingBar barraCalificacion;
    private EditText editTextEmail, editTextDescripcion; // Añadido
    private Button btnAccesoAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resena);

        spinnerMotivo = findViewById(R.id.spinner);
        barraCalificacion = findViewById(R.id.ratingBar);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress); // Inicializado
        editTextDescripcion = findViewById(R.id.editTextTextMultiLine2); // Inicializado

        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_resena.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextEmail.getText().toString(); // Obtener el correo
                String motivo = spinnerMotivo.getSelectedItem().toString(); // Obtener el motivo
                int calificacion = (int) barraCalificacion.getRating(); // Obtener la calificación
                String descripcion = editTextDescripcion.getText().toString(); // Obtener la descripción

                // Comprobar que los campos no estén vacíos
                if (correo.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(activity_resena.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Agregar la reseña a la base de datos
                ResenaOpenHelper baseDatosResena = new ResenaOpenHelper(activity_resena.this);
                baseDatosResena.agregarResena(correo, motivo, calificacion, descripcion);

                Toast.makeText(activity_resena.this, "Gracias por su reseña", Toast.LENGTH_LONG).show();
                // Limpiar campos
                editTextEmail.setText("");
                editTextDescripcion.setText("");
                barraCalificacion.setRating(0);
            }
        });

        // Configuración del botón para acceder al modo administrador
        btnAccesoAdmin = findViewById(R.id.btnAccesoAdmin);
        btnAccesoAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoContraseña();
            }
        });
    }

    private void mostrarDialogoContraseña() {
        // Crear un campo de entrada para la contraseña
        final EditText entradaContraseña = new EditText(this);
        entradaContraseña.setHint("Ingresa la contraseña");

        new AlertDialog.Builder(this)
                .setTitle("Acceso Admin")
                .setMessage("Ingresa la contraseña para acceder")
                .setView(entradaContraseña)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String contraseña = entradaContraseña.getText().toString();
                        if (verificarContraseña(contraseña)) {
                            Intent intent = new Intent(activity_resena.this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(activity_resena.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private boolean verificarContraseña(String contraseña) {
        final String CONTRASEÑA_ADMIN = "admin123";
        return CONTRASEÑA_ADMIN.equals(contraseña);
    }
}
