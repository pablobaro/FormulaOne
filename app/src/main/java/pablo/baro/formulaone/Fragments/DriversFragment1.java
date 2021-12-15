package pablo.baro.formulaone.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.DriversTabbed;
import pablo.baro.formulaone.R;
import pablo.baro.formulaone.adapters.DriverAdapter;
import pablo.baro.formulaone.driverComplete;
import pablo.baro.formulaone.model.DriversModel;

public class DriversFragment1 extends Fragment {
    FragmentManager fm;

    OnFragmentInteractionListener mInterfaz;

    public DriversFragment1() {

    }
    RecyclerView recyclerView;
    List<DriversModel> drivers;
    private boolean mTwoPane = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_drivers1, container, false);
        drivers = new ArrayList<>();
        recyclerView = vista.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        drivers = getAllDrivers();

        DriverAdapter adapter = new DriverAdapter(getContext(), drivers);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DriverAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (DriversTabbed.getTwoPane()) {
                    int selectedDriver = position;

                    DriverDetailFragment fragment = DriverDetailFragment.newInstance(selectedDriver);
                    DriversTabbed.getSupportFragment().beginTransaction()
                            .replace(R.id.driver_add_to_favs_layout, fragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d("Piloto Actual: ", drivers.get(selectedDriver).getName());
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, driverComplete.class);
                    intent.putExtra(DriversModel.DRIVER_KEY, position);
                    context.startActivity(intent);
                }
            }
        });
        return vista;
    }

    public List<DriversModel> getAllDrivers(){
        return new ArrayList<DriversModel>(){{
            add(new DriversModel(1,"Max", "Verstappen", "Dutch", "33", R.drawable.maxverstappen));
            add(new DriversModel(2,"Sergio", "Pérez", "Mexican", "11", R.drawable.sergioperez));
            add(new DriversModel(3,"Lewis", "Hamilton", "British", "44", R.drawable.lewishamilton));
            add(new DriversModel(4,"Valtteri", "Bottas", "Finnish", "77", R.drawable.valtteribottas));
            add(new DriversModel(5,"Carlos", "Sainz", "Spanish", "55", R.drawable.carlossainz));
            add(new DriversModel(6,"Charles", "Leclerc", "Monegasque", "16", R.drawable.charlesleclerc));
            add(new DriversModel(7,"Fernando", "Alonso", "Spanish", "14", R.drawable.fernandoalonso));
            add(new DriversModel(8,"Esteban", "Ocon", "French", "31", R.drawable.estebanocon));
            add(new DriversModel(9,"Lando", "Norris", "British", "4", R.drawable.landonorris));
            add(new DriversModel(10,"Daniel", "Ricciardo", "Australian", "3", R.drawable.dannielricciardo));
            add(new DriversModel(11,"Kimi", "Räikkönen", "Finnish", "7", R.drawable.kimiraikkonen));
            add(new DriversModel(12,"Antonio", "Giovinazzi", "Italian", "99", R.drawable.giovinnazi));
            add(new DriversModel(13,"Sebastian", "Vettel", "German", "5", R.drawable.vettel));
            add(new DriversModel(14,"Lance", "Stroll", "Canadian", "18", R.drawable.stroll));
            add(new DriversModel(15,"Pierre", "Gasly", "French", "10", R.drawable.gasly));
            add(new DriversModel(16,"Yuki", "Tsunoda", "Japanese", "22", R.drawable.yuki));
            add(new DriversModel(17,"George", "Russell", "British", "63", R.drawable.russell));
            add(new DriversModel(18,"Nicholas", "Latifi", "Canadian", "6", R.drawable.latifi));
            add(new DriversModel(19,"Mick", "Schumacher", "German", "47", R.drawable.mick));
            add(new DriversModel(20,"Nikita", "Mazepin", "Russian", "9", R.drawable.mazepin));
        }};
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mInterfaz= (OnFragmentInteractionListener) context;
        }
    }

    public interface OnFragmentInteractionListener{
        DriversModel getDriver(String name);
    }


}