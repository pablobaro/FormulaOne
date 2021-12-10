package pablo.baro.formulaone.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.adapters.FavouriteAdapter;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.driverComplete;
import pablo.baro.formulaone.model.DriversModel;

public class DriverDetailFragment extends Fragment implements View.OnClickListener {
    public DriverDetailFragment() {
        // Required empty public constructor
    }

    Button addFav;
    public DriversModel nDriver;
    private DriverViewModel mDriverViewModel;

    public static DriverDetailFragment newInstance(int selectedMail){
        DriverDetailFragment fragment = new DriverDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(DriversModel.DRIVER_KEY, selectedMail);
        fragment.setArguments(arguments);
        return fragment;
    }

    List<DriversModel> drivers;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(DriversModel.DRIVER_KEY)){
            nDriver = DriversModel.DRIVER_LIST.get(getArguments().getInt(DriversModel.DRIVER_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_driver_detail, container, false);
        if (nDriver!=null){
            String nNombre = nDriver.getName() + " " + nDriver.getSurname();
            ((TextView) rootView.findViewById(R.id.nameDriverFragment)).setText(nNombre);
            ((TextView) rootView.findViewById(R.id.nationalityDriverFragment)).setText(nDriver.getNationality());
            ((TextView) rootView.findViewById(R.id.numberDriverFragment)).setText(nDriver.getNumber());
            ((ImageView) rootView.findViewById(R.id.completeImageFragment)).setImageResource(nDriver.getImg());
            addFav = rootView.findViewById(R.id.buttonFavsFragment);
            addFav.setOnClickListener(this);
        }

        drivers = new ArrayList<>();
        List<DriversModel> drivers = DriversModel.DRIVER_LIST;

        FavouriteAdapter adapter = new FavouriteAdapter(getContext(), drivers);
        RecyclerView rv = rootView.findViewById(R.id.favouriteDriversFragment);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mDriverViewModel = new DriverViewModel(this.getActivity().getApplication());
        mDriverViewModel.getAllWords().observe(getViewLifecycleOwner(), new Observer<List<DriversModel>>() {
            @Override
            public void onChanged(List<DriversModel> driversModels) {
                adapter.setDrivers(driversModels);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getLayoutPosition();
                DriversModel myWord = adapter.getWordAtPosition(position);
                Toast.makeText(getContext(), "Deleting " +
                        myWord.getName(), Toast.LENGTH_LONG).show();

                mDriverViewModel.deleteWord(myWord);
            }
        });

        helper.attachToRecyclerView(rv);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        mDriverViewModel.insert(nDriver);
    }
}