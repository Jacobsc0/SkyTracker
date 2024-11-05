package com.example.skytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class ResenaOpenHelper {

    private SQLiteDatabase db;

    public ResenaOpenHelper(Context context) {
        // Crear o abrir la base de datos
        db = context.openOrCreateDatabase("BD_RESEÑAS", Context.MODE_PRIVATE, null);
        // Crear la tabla de reseñas si no existe
        db.execSQL("CREATE TABLE IF NOT EXISTS reseña(id INTEGER PRIMARY KEY AUTOINCREMENT, correo TEXT, motivo TEXT, calificacion INTEGER, descripcion TEXT)");
    }

    // Método para insertar una reseña
    public void agregarResena(String correo, String motivo, int calificacion, String descripcion) {
        String sql = "INSERT INTO reseña(correo, motivo, calificacion, descripcion) VALUES (?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, correo);
        statement.bindString(2, motivo);
        statement.bindLong(3, calificacion);
        statement.bindString(4, descripcion);
        statement.execute();
    }


    public List<Resena> obtenerTodasLasResenas() {
        List<Resena> listaResenas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM reseña", null);

        int idIndex = cursor.getColumnIndex("id");
        int correoIndex = cursor.getColumnIndex("correo");
        int motivoIndex = cursor.getColumnIndex("motivo");
        int calificacionIndex = cursor.getColumnIndex("calificacion");
        int descripcionIndex = cursor.getColumnIndex("descripcion");

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(idIndex);
                String correo = cursor.getString(correoIndex);
                String motivo = cursor.getString(motivoIndex);
                int calificacion = cursor.getInt(calificacionIndex);
                String descripcion = cursor.getString(descripcionIndex);

                Resena resena = new Resena(id, correo, motivo, calificacion, descripcion);
                listaResenas.add(resena);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaResenas;
    }


    public void actualizarResena(int id, String correo, String motivo, int calificacion, String descripcion) {
        String sql = "UPDATE reseña SET correo = ?, motivo = ?, calificacion = ?, descripcion = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, correo);
        statement.bindString(2, motivo);
        statement.bindLong(3, calificacion);
        statement.bindString(4, descripcion);
        statement.bindLong(5, id);
        statement.execute();
    }


    public void eliminarResena(int id) {
        String sql = "DELETE FROM reseña WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindLong(1, id);
        statement.execute();
    }
}
