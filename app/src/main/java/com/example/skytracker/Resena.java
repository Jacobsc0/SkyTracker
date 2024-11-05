package com.example.skytracker;

public class Resena {

    private int id;
    private String correo;
    private String motivo;
    private int calificacion;
    private String descripcion;

    public Resena(int id, String correo, String motivo, int calificacion, String descripcion) {
        this.id = id;
        this.correo = correo;
        this.motivo = motivo;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getCorreo() { return correo; }
    public String getMotivo() { return motivo; }
    public int getCalificacion() { return calificacion; }
    public String getDescripcion() { return descripcion; }

    public void setId(int id) { this.id = id; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
