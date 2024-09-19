import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClimaDetalladoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClimaAdapter adapter;
    private ArrayList<ClimaDetallado> climaDetalladoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detallado);

        recyclerView = findViewById(R.id.recyclerViewClimaDetallado);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Simulando datos de clima
        climaDetalladoList = new ArrayList<>();
        climaDetalladoList.add(new ClimaDetallado("17:00", "21°", "Soleado", "Oeste", "15-34 km/h", "2 Bajo"));
        climaDetalladoList.add(new ClimaDetallado("18:00", "20°", "Soleado", "Oeste", "14-34 km/h", "1 Bajo"));
        climaDetalladoList.add(new ClimaDetallado("19:00", "17°", "Soleado", "Oeste", "13-30 km/h", "0 Bajo"));
        // ... Agrega más datos según sea necesario

        adapter = new ClimaAdapter(climaDetalladoList);
        recyclerView.setAdapter(adapter);
    }
}
