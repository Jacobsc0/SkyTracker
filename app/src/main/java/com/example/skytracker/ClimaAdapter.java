package com.example.skytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder> {

    private ArrayList<ClimaDetallado> climaList;

    public ClimaAdapter(ArrayList<ClimaDetallado> climaList) {
        this.climaList = climaList;
    }

    @NonNull
    @Override
    public ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clima, parent, false);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClimaViewHolder holder, int position) {
        ClimaDetallado clima = climaList.get(position);
        holder.textHora.setText(clima.getHora());
        holder.textTemperatura.setText(clima.getTemperatura());
        holder.textEstadoClima.setText(clima.getCondiciones());

        holder.imageClima.setImageResource(R.drawable.sol);
    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }

    public static class ClimaViewHolder extends RecyclerView.ViewHolder {
        TextView textHora, textTemperatura, textEstadoClima;
        ImageView imageClima;

        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);
            textHora = itemView.findViewById(R.id.textHora);
            textTemperatura = itemView.findViewById(R.id.textTemperatura);
            textEstadoClima = itemView.findViewById(R.id.textEstadoClima);
            imageClima = itemView.findViewById(R.id.imageClima);
        }
    }
}
