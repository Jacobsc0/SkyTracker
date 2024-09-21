package com.example.skytracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout cityContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityContainer = findViewById(R.id.cityContainer);
        Button addCityButton = findViewById(R.id.button_add_city);
        Button btnResena = findViewById(R.id.btnResena);


        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCityDialog();
            }
        });


        btnResena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_resena.class);
                startActivity(intent);
            }
        });
    }


    private void showAddCityDialog() {
        final EditText input = new EditText(MainActivity.this);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Agregar Ciudad")
                .setMessage("Ingresa el nombre de la ciudad:")
                .setView(input)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityName = input.getText().toString().trim();
                        if (!cityName.isEmpty()) {
                            addCityToContainer(cityName);
                        } else {
                            Toast.makeText(MainActivity.this, "El nombre de la ciudad no puede estar vacío", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    private void addCityToContainer(final String cityName) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View cityView = inflater.inflate(R.layout.city_layout, cityContainer, false);

        TextView cityNameTextView = cityView.findViewById(R.id.textView3);
        cityNameTextView.setText(cityName);

        cityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClimaDetalladoActivity.class);
                intent.putExtra("ciudadNombre", cityName);
                startActivity(intent);
            }
        });

        cityContainer.addView(cityView);
    }
}
