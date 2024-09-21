package com.example.skytracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Resena extends AppCompatActivity {

    private Spinner motivoSpinner;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resena);


        motivoSpinner = findViewById(R.id.spinner);


        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Resena.this, activity_main.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Resena.this, "Gracias por contactarnos", Toast.LENGTH_LONG).show();
            }
        });


        ratingBar = findViewById(R.id.ratingBar);
    }
}
