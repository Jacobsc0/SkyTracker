import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.horaTextView.setText(clima.getHora());
        holder.temperaturaTextView.setText(clima.getTemperatura());
        holder.condicionesTextView.setText(clima.getCondiciones());
        holder.vientoTextView.setText(clima.getViento());
        holder.sensacionTextView.setText(clima.getSensacion());
        holder.riesgoTextView.setText(clima.getRiesgo());
    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }

    public class ClimaViewHolder extends RecyclerView.ViewHolder {
        TextView horaTextView, temperaturaTextView, condicionesTextView, vientoTextView, sensacionTextView, riesgoTextView;

        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);
            horaTextView = itemView.findViewById(R.id.horaTextView);
            temperaturaTextView = itemView.findViewById(R.id.temperaturaTextView);
            condicionesTextView = itemView.findViewById(R.id.condicionesTextView);
            vientoTextView = itemView.findViewById(R.id.vientoTextView);
            sensacionTextView = itemView.findViewById(R.id.sensacionTextView);
            riesgoTextView = itemView.findViewById(R.id.riesgoTextView);
        }
    }
}
