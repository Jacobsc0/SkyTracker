package com.example.skytracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout cityContainer;
    private APIClima apiClima;
    private final List<String> listaCiudades = new ArrayList<>(Arrays.asList(
            "Madrid", "Barcelona", "Buenos Aires", "Santiago", "Ciudad de México", "Lima", "Bogotá", "Ovalle"
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityContainer = findViewById(R.id.cityContainer);
        Button AgregarCiudad = findViewById(R.id.button_add_city);
        Button btnResena = findViewById(R.id.btnResena);
        apiClima = new APIClima(this);


        AgregarCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarListaCiudadesDialog();
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


    private void mostrarListaCiudadesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Selecciona una ciudad");

        // Convertir la lista de ciudades a un arreglo
        String[] ciudadesArray = listaCiudades.toArray(new String[0]);

        builder.setItems(ciudadesArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ciudadSeleccionada = ciudadesArray[which];
                agregarCiudadAlContenedor(ciudadSeleccionada);
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }



    private void agregarCiudadAlContenedor(final String nombreCiudad) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View vistaCiudad = inflater.inflate(R.layout.city_layout, cityContainer, false);

        TextView nombreCiudadTextView = vistaCiudad.findViewById(R.id.textViewNombreCiudad);
        nombreCiudadTextView.setText(nombreCiudad); // Asegúrate de que estás configurando el nombre de la ciudad

        vistaCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClimaDetalladoActivity.class);
                intent.putExtra("ciudadNombre", nombreCiudad); // Pasa correctamente el nombre de la ciudad
                startActivity(intent);
            }
        });

        cityContainer.addView(vistaCiudad); // Agrega la vista de la ciudad al contenedor
    }



        private void obtenerClima(String nombreCiudad, TextView temperaturaTextView, ImageView imagenClima) {
        apiClima.obtenerClimaPorCiudad(nombreCiudad, new APIClima.ClimaCallback() {
            @Override
            public void onSuccess(JSONObject datosClima) {
                try {
                    JSONObject main = datosClima.getJSONObject("main");
                    double temperatura = main.getDouble("temp");
                    String descripcion = datosClima.getJSONArray("weather").getJSONObject(0).getString("description");

                    temperaturaTextView.setText("Temperatura: " + temperatura + " °C");

                    if (descripcion.contains("clear")) {
                        imagenClima.setImageResource(R.drawable.sol);
                    } else if (descripcion.contains("rain")) {
                        imagenClima.setImageResource(R.drawable.lluvia);
                        imagenClima.setImageResource(R.drawable.nube);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al procesar los datos del clima", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String mensajeError) {
                Toast.makeText(MainActivity.this, mensajeError, Toast.LENGTH_SHORT).show();
            }
        });
    }




}
