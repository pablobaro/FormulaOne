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
import android.widget.Toast;

import java.util.List;

import pablo.baro.formulaone.R;
import pablo.baro.formulaone.adapters.FavouriteAdapter;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.model.DriversModel;

public class DriversFragment2 extends Fragment {
    List<DriversModel> drivers = DriversModel.DRIVER_LIST;
    private DriverViewModel mDriverViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_drivers2, container, false);
        FavouriteAdapter adapter = new FavouriteAdapter(getContext(), drivers);
        RecyclerView rv = vista.findViewById(R.id.favouriteDrivers);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mDriverViewModel = new DriverViewModel(getActivity().getApplication());
        mDriverViewModel.getAllDrivers().observe(getActivity(), new Observer<List<DriversModel>>() {
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

                mDriverViewModel.deleteDriver(myWord);
            }
        });

        helper.attachToRecyclerView(rv);

        return vista;
    }
}