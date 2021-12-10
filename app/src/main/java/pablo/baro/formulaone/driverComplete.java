package pablo.baro.formulaone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pablo.baro.formulaone.adapters.FavouriteAdapter;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.model.DriversModel;

public class driverComplete extends AppCompatActivity {
    private DriversModel driver;

    private DriverViewModel mDriverViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_complete);
        List<DriversModel> drivers = DriversModel.DRIVER_LIST;
        driver = DriversModel.DRIVER_LIST.get(getIntent().getIntExtra(DriversModel.DRIVER_KEY,0));

        if (driver!=null){
            String nNombre = driver.getName() + " " + driver.getSurname();
            ((TextView) findViewById(R.id.nameDriverComplete)).setText(nNombre);
            ((TextView) findViewById(R.id.nationalityDriverComplete)).setText(driver.getNationality());
            ((TextView) findViewById(R.id.numberDriverComplete)).setText(driver.getNumber());
            ((ImageView) findViewById(R.id.completeImage)).setImageResource(driver.getImg());
        }

        FavouriteAdapter adapter = new FavouriteAdapter(getApplicationContext(), drivers);
        RecyclerView rv = findViewById(R.id.favouriteDriversFragment);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mDriverViewModel = new DriverViewModel(this.getApplication());
        mDriverViewModel.getAllWords().observe(this, new Observer<List<DriversModel>>() {
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
                Toast.makeText(driverComplete.this, "Deleting " +
                        myWord.getName(), Toast.LENGTH_LONG).show();

                mDriverViewModel.deleteWord(myWord);
            }
        });

        helper.attachToRecyclerView(rv);
    }

    public void favs(View view){
        mDriverViewModel.insert(driver);
    }
}