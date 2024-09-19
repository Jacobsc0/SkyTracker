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

        // Referencia al contenedor de las ciudades y al botón para agregar ciudad
        cityContainer = findViewById(R.id.cityContainer);
        Button addCityButton = findViewById(R.id.button_add_city);

        // Acción cuando se presiona el botón "Agregar Ciudad"
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCityDialog();
            }
        });
    }

    // Método para mostrar el diálogo que permite agregar una nueva ciudad
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

                        // Validar que el nombre de la ciudad no esté vacío
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

    // Método para añadir una nueva ciudad al contenedor
    private void addCityToContainer(final String cityName) {
        // Inflar el layout de la ciudad que será un CardView
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View cityView = inflater.inflate(R.layout.city_layout, cityContainer, false);

        // Asignar el nombre de la ciudad al TextView del CardView
        TextView cityNameTextView = cityView.findViewById(R.id.textView3);
        cityNameTextView.setText(cityName);

        // Agregar el evento de click al CardView para abrir la actividad con el clima detallado
        cityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear intent para abrir la actividad de clima detallado
                Intent intent = new Intent(MainActivity.this, ClimaDetalladoActivity.class);
                intent.putExtra("ciudadNombre", cityName); // Pasar el nombre de la ciudad
                startActivity(intent); // Iniciar la actividad
            }
        });

        // Agregar la nueva ciudad al contenedor
        cityContainer.addView(cityView);
    }
}
