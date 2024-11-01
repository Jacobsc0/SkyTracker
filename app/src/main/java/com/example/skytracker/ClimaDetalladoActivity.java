package com.example.skytracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ClimaDetalladoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewClimaDetallado;
    private ClimaAdapter climaAdapter;
    private List<Clima> listaClima; // Lista para almacenar los datos del clima
    private APIClima apiClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detallado);

        textViewDescripcion = findViewById(R.id.textViewDescripcion);
        textViewTemperatura = findViewById(R.id.textViewTemperatura);
        textViewVelocidadViento = findViewById(R.id.textViewVelocidadViento);
        textViewHoraPuestaSol = findViewById(R.id.textViewHoraPuestaSol);
        Button buttonRegresar = findViewById(R.id.buttonRegresar);

        listaClima = new ArrayList<>(); // Inicializa la lista de clima
        climaAdapter = new ClimaAdapter(listaClima); // Crea el adaptador

        recyclerViewClimaDetallado.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewClimaDetallado.setAdapter(climaAdapter); // Asigna el adaptador al RecyclerView

        String ciudadNombre = getIntent().getStringExtra("ciudadNombre");
        apiClima = new APIClima(this);

        apiClima.obtenerClimaPorCiudad(ciudadNombre, new APIClima.ClimaCallback() {
            @Override
            public void onSuccess(JSONObject datosClima) {
                mostrarDatosClima(datosClima);
            }

            @Override
            public void onError(String mensajeError) {
                Toast.makeText(ClimaDetalladoActivity.this, "Error: " + mensajeError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrarDatosClima(JSONObject datosClima) {
        try {
            String descripcion = datosClima.getJSONArray("current").getJSONObject(0).getString("weather").getJSONObject(0).getString("description");
            double temperatura = datosClima.getJSONObject("current").getDouble("temp");

            double velocidadViento = datosClima.getJSONObject("current").getDouble("wind_speed");
            long horaPuestaSol = datosClima.getJSONObject("current").getLong("sunset");

            String horaPuestaSolLocal = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(horaPuestaSol * 1000));

            textViewDescripcion.setText(descripcion);
            textViewTemperatura.setText(String.valueOf(temperatura) + " °C");
            textViewVelocidadViento.setText(String.valueOf(velocidadViento) + " m/s");
            textViewHoraPuestaSol.setText(horaPuestaSolLocal);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos del clima.", Toast.LENGTH_SHORT).show();
        }
    }

}
