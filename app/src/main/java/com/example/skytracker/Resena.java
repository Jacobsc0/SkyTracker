package com.example.skytracker;

public class Resena {
    private String id;
    private String correo;
    private String motivo;
    private String descripcion;
    private float calificacion;


    public Resena() {}

    public Resena(String id, String correo, String motivo, String descripcion, float calificacion) {
        this.id = id;
        this.correo = correo;
        this.motivo = motivo;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public float getCalificacion() { return calificacion; }
    public void setCalificacion(float calificacion) { this.calificacion = calificacion; }
}
