package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.adapters.ScheduleAdapter;
import pablo.baro.formulaone.controllers.ScheduleRepository;
import pablo.baro.formulaone.model.ScheduleModel;

public class Schedule extends AppCompatActivity {
    ScheduleViewModel viewModel;
    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    ArrayList<ScheduleModel> schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.scheduleRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        schedule = new ArrayList<>();
        parseJSON();
    }

    public void parseJSON() {
        viewModel= new Schedule.ScheduleViewModel(this.getApplication());
        viewModel.getSchedule().observe(this, new Observer<List<ScheduleModel>>() {
            @Override
            public void onChanged(List<ScheduleModel> driverClasses) {
                if (driverClasses != null) {
                    for (int i = 0; i < driverClasses.size(); i++) {
                        ScheduleModel scheduleModel = new ScheduleModel(driverClasses.get(i).getSeason(), driverClasses.get(i).getRound(), driverClasses.get(i).getRaceName(), driverClasses.get(i).getDate(), driverClasses.get(i).getInformation());
                        schedule.add(scheduleModel);
                    }
                    adapter = new ScheduleAdapter(Schedule.this, schedule);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    public class ScheduleViewModel extends AndroidViewModel {
        ScheduleRepository repository;
        String round;

        public ScheduleViewModel(@NonNull Application application) {
            super(application);

            repository = new ScheduleRepository(application);
        }

        public LiveData<List<ScheduleModel>> getSchedule(){
            return repository.getSchedule();
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }
    }
}