package com.example.skytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder> {

    private List<Clima> listaClima;

    public ClimaAdapter(List<Clima> listaClima) {
        this.listaClima = listaClima;
    }

    @NonNull
    @Override
    public ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clima, parent, false);
        return new ClimaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ClimaViewHolder holder, int position) {
        Clima clima = listaClima.get(position);
        holder.descripcionTextView.setText(clima.getDescripcion());
        holder.temperaturaTextView.setText(String.valueOf(clima.getTemperatura()) + "°C");
    }

    @Override
    public int getItemCount() {
        return listaClima.size();
    }

    static class ClimaViewHolder extends RecyclerView.ViewHolder {
        TextView descripcionTextView;
        TextView temperaturaTextView;

        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcionTextView = itemView.findViewById(R.id.textViewDescripcion);
            temperaturaTextView = itemView.findViewById(R.id.textViewTemperatura);
        }
    }
}
