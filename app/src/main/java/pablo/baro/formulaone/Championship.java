package pablo.baro.formulaone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.widget.SeekBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.adapters.ChampionshipAdapter;
import pablo.baro.formulaone.controllers.ChampionshipRepository;
import pablo.baro.formulaone.databinding.ActivityMainBinding;
import pablo.baro.formulaone.model.ChampionshipModel;

public class Championship extends AppCompatActivity {
    private final ArrayList<ChampionshipModel> drivers = new ArrayList<>();
    ChampionshipViewModel viewModel;
    private RecyclerView recyclerView;
    private ChampionshipAdapter adapter;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championship);

        recyclerView = findViewById(R.id.driversRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //drivers = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        SeekBar roundBar = findViewById(R.id.seekBar);

        adapter = new ChampionshipAdapter(Championship.this, drivers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        roundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String nRound=String.valueOf(progress);

                parseJSON(nRound);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void parseJSON(String round) {
        viewModel= new ChampionshipViewModel(this.getApplication(), round);
        viewModel.getChampionship().observe(this, new Observer<List<ChampionshipModel>>() {
            @Override
            public void onChanged(List<ChampionshipModel> driverClasses) {
                if (driverClasses != null) {
                    ArrayList<ChampionshipModel> addDrivers = new ArrayList<>();
                    for (int i = 0; i < driverClasses.size(); i++) {
                        ChampionshipModel driver = new ChampionshipModel(driverClasses.get(i).getPosition(),driverClasses.get(i).getName(), driverClasses.get(i).getSurname(), driverClasses.get(i).getPoints(), driverClasses.get(i).getConstructorName());
                        addDrivers.add(driver);
                    }
                    adapter.loadData(addDrivers);
                }
            }
        });
    }

    public class ChampionshipViewModel extends AndroidViewModel{
        ChampionshipRepository repository;
        String round;

        public ChampionshipViewModel(@NonNull Application application, String round) {
            super(application);

            repository = new ChampionshipRepository(application, round);
        }

        public LiveData<List<ChampionshipModel>> getChampionship(){
            return repository.getChampionship();
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }
    }
}