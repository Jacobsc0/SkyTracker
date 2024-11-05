package com.example.skytracker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClimaDetalladoActivity extends AppCompatActivity {

    private TextView textViewTituloCiudad;
    private TextView textViewVelocidadViento;
    private TextView textViewHoraPuestaSol;
    private TextView textViewClimaPorHora;
    private TextView textViewDetallesAdicionales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detallado);

        textViewTituloCiudad = findViewById(R.id.textViewTituloCiudad);
        textViewVelocidadViento = findViewById(R.id.textViewVelocidadViento);
        textViewHoraPuestaSol = findViewById(R.id.textViewHoraPuestaSol);
        textViewClimaPorHora = findViewById(R.id.textViewClimaPorHora);
        textViewDetallesAdicionales = findViewById(R.id.textViewDetallesAdicionales);

        String ciudadNombre = getIntent().getStringExtra("ciudadNombre");
        textViewTituloCiudad.setText(ciudadNombre);

        APIClima apiClima = new APIClima(this);
        apiClima.obtenerClimaPorCiudad(ciudadNombre, new APIClima.ClimaCallback() {
            @Override
            public void onSuccess(JSONObject datosClima) {
                try {
                    JSONObject viento = datosClima.getJSONObject("wind");
                    double velocidadViento = viento.getDouble("speed");
                    textViewVelocidadViento.setText("Velocidad del Viento: " + velocidadViento + " m/s");

                    // Obtener la hora de la puesta del sol
                    long horaPuestaSol = datosClima.getJSONObject("sys").getLong("sunset") * 1000; // Convertir a milisegundos
                    String horaPuestaSolStr = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(horaPuestaSol));
                    textViewHoraPuestaSol.setText("Hora de Puesta del Sol: " + horaPuestaSolStr);

                    obtenerClimaPorHora(ciudadNombre);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String mensajeError) {
                // Manejar el error
                textViewDetallesAdicionales.setText("Error: " + mensajeError);
            }
        });
    }

    private void obtenerClimaPorHora(String ciudadNombre) {

        String dummyData = "10:00 - 22°C\n11:00 - 23°C\n12:00 - 24°C\n13:00 - 25°C\n14:00 - 26°C";
        textViewClimaPorHora.setText("Clima por Hora:\n" + dummyData);

        String detallesAdicionales = "Nubes: 10%\nHumedad: 60%\nPresión: 1012 hPa";
        textViewDetallesAdicionales.setText("Detalles Adicionales:\n" + detallesAdicionales);
    }
}
