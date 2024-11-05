package com.example.skytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResenaAdapter extends RecyclerView.Adapter<ResenaAdapter.ResenaViewHolder> {

    private List<Resena> listaResenas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditarClick(Resena resena);
        void onEliminarClick(Resena resena);
    }

    public ResenaAdapter(List<Resena> listaResenas, OnItemClickListener listener) {
        this.listaResenas = listaResenas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resena, parent, false);
        return new ResenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResenaViewHolder holder, int position) {
        Resena resena = listaResenas.get(position);
        holder.textCorreo.setText(resena.getCorreo());
        holder.textMotivo.setText(resena.getMotivo());
        holder.textDescripcion.setText(resena.getDescripcion());
        holder.textCalificacion.setText(String.valueOf(resena.getCalificacion()));

        holder.btnEditar.setOnClickListener(v -> listener.onEditarClick(resena));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminarClick(resena));
    }

    @Override
    public int getItemCount() {
        return listaResenas.size();
    }

    public static class ResenaViewHolder extends RecyclerView.ViewHolder {
        TextView textCorreo, textMotivo, textDescripcion, textCalificacion;
        Button btnEditar, btnEliminar;

        public ResenaViewHolder(@NonNull View itemView) {
            super(itemView);
            textCorreo = itemView.findViewById(R.id.textCorreo);
            textMotivo = itemView.findViewById(R.id.textMotivo);
            textDescripcion = itemView.findViewById(R.id.textDescripcion);
            textCalificacion = itemView.findViewById(R.id.textCalificacion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
