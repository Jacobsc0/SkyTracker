package com.example.skytracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_resena extends AppCompatActivity {

    private EditText editTextCorreo, editTextDescripcion;
    private Spinner spinnerMotivo;
    private RatingBar ratingBar;
    private Button btnEnviar, btnRegresar, btnAccesoAdmin;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resena);


        editTextCorreo = findViewById(R.id.editTextTextEmailAddress);
        spinnerMotivo = findViewById(R.id.spinner);
        editTextDescripcion = findViewById(R.id.editTextTextMultiLine2);
        ratingBar = findViewById(R.id.ratingBar);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnAccesoAdmin = findViewById(R.id.btnAccesoAdmin);


        databaseReference = FirebaseDatabase.getInstance().getReference("resenas");

        btnEnviar.setOnClickListener(v -> enviarResena());

        btnRegresar.setOnClickListener(v -> finish());

        btnAccesoAdmin.setOnClickListener(v -> mostrarDialogoContraseña());
    }

    private void enviarResena() {
        String correo = editTextCorreo.getText().toString().trim();
        String motivo = spinnerMotivo.getSelectedItem().toString();
        String descripcion = editTextDescripcion.getText().toString().trim();
        float calificacion = ratingBar.getRating();

        if (correo.isEmpty() || descripcion.isEmpty() || calificacion == 0) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseReference.push().getKey();
        Resena resena = new Resena(id, correo, motivo, descripcion, calificacion);

        assert id != null;
        databaseReference.child(id).setValue(resena).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Reseña enviada correctamente.", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "Error al enviar la reseña.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos() {
        editTextCorreo.setText("");
        editTextDescripcion.setText("");
        spinnerMotivo.setSelection(0);
        ratingBar.setRating(0);
    }

    private void mostrarDialogoContraseña() {
        final EditText entradaContraseña = new EditText(this);
        entradaContraseña.setHint("Ingresa la contraseña");
        entradaContraseña.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

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
