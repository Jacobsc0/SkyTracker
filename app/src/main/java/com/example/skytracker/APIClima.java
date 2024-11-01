package com.example.skytracker;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class APIClima {

    private static final String URL_BASE = "https://api.openweathermap.org/data/2.5/onecall";
    private static final String CLAVE_API = "295c5454ba024b168e388d496d7a759a";
    private RequestQueue colaSolicitud;

    public APIClima(Context contexto) {
        this.colaSolicitud = Volley.newRequestQueue(contexto);
    }

    public void obtenerClimaPorCiudad(String ciudad, final ClimaCallback callback) {
        // Primero obtenemos las coordenadas de la ciudad
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&appid=" + CLAVE_API + "&units=metric&lang=es";

        StringRequest solicitud = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuesta) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(respuesta);
                            double latitud = jsonRespuesta.getJSONObject("coord").getDouble("lat");
                            double longitud = jsonRespuesta.getJSONObject("coord").getDouble("lon");

                            obtenerClimaDetallado(latitud, longitud, callback);
                        } catch (Exception e) {
                            callback.onError("Error al procesar los datos de la ciudad");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error en la solicitud: " + error.getMessage());
                    }
                });

        colaSolicitud.add(solicitud);
    }

    private void obtenerClimaDetallado(double latitud, double longitud, final ClimaCallback callback) {
        String url = URL_BASE + "?lat=" + latitud + "&lon=" + longitud + "&appid=" + CLAVE_API + "&units=metric&lang=es";

        StringRequest solicitud = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuesta) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(respuesta);
                            callback.onSuccess(jsonRespuesta);
                        } catch (Exception e) {
                            callback.onError("Error al procesar los datos del clima detallado");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Error en la solicitud: " + error.getMessage());
                    }
                });

        colaSolicitud.add(solicitud);
    }

    public interface ClimaCallback {
        void onSuccess(JSONObject datosClima);
        void onError(String mensajeError);
    }
}
