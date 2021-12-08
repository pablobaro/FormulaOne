package pablo.baro.formulaone.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.adapters.DriverAdapter;
import pablo.baro.formulaone.model.DriversModel;

public class DriversFragment1 extends Fragment {

    public DriversFragment1() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    List<DriversModel> drivers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_drivers1, container, false);
        drivers = new ArrayList();
        recyclerView = vista.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        drivers = getAllDrivers();

        DriverAdapter adapter = new DriverAdapter(getContext(), drivers);
        recyclerView.setAdapter(adapter);
        return vista;
    }

    private List<DriversModel> getAllDrivers(){
        return new ArrayList<DriversModel>(){{
            add(new DriversModel("Max", "Verstappen", "Dutch", "33", R.drawable.maxverstappen));
            add(new DriversModel("Sergio", "Pérez", "Mexican", "11", R.drawable.sergioperez));
            add(new DriversModel("Lewis", "Hamilton", "British", "44", R.drawable.lewishamilton));
            add(new DriversModel("Valtteri", "Bottas", "Finnish", "77", R.drawable.valtteribottas));
            add(new DriversModel("Carlos", "Sainz", "Spanish", "55", R.drawable.carlossainz));
            add(new DriversModel("Charles", "Leclerc", "Monegasque", "16", R.drawable.charlesleclerc));
            add(new DriversModel("Fernando", "Alonso", "Spanish", "14", R.drawable.fernandoalonso));
            add(new DriversModel("Esteban", "Ocon", "French", "31", R.drawable.estebanocon));
            add(new DriversModel("Lando", "Norris", "British", "4", R.drawable.landonorris));
            add(new DriversModel("Daniel", "Ricciardo", "Australian", "3", R.drawable.dannielricciardo));
            add(new DriversModel("Kimi", "Räikkönen", "Finnish", "7", R.drawable.kimiraikkonen));
            add(new DriversModel("Antonio", "Giovinazzi", "Italian", "99", R.drawable.giovinnazi));
            add(new DriversModel("Sebastian", "Vettel", "German", "5", R.drawable.vettel));
            add(new DriversModel("Lance", "Stroll", "Canadian", "18", R.drawable.stroll));
            add(new DriversModel("Pierre", "Gasly", "French", "10", R.drawable.gasly));
            add(new DriversModel("Yuki", "Tsunoda", "Japanese", "22", R.drawable.yuki));
            add(new DriversModel("George", "Russell", "British", "63", R.drawable.russell));
            add(new DriversModel("Nicholas", "Latifi", "Canadian", "6", R.drawable.latifi));
            add(new DriversModel("Mick", "Schumacher", "German", "47", R.drawable.mick));
            add(new DriversModel("Nikita", "Mazepin", "Russian", "9", R.drawable.mazepin));
        }};
    }
}