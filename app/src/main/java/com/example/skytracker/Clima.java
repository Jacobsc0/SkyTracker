package com.example.skytracker;

public class Clima {
    private String descripcion;
    private double temperatura;

    public Clima(String descripcion, double temperatura) {
        this.descripcion = descripcion;
        this.temperatura = temperatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
