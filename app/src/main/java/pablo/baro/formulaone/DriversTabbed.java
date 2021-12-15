package pablo.baro.formulaone;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import pablo.baro.formulaone.Fragments.DriversFragment1;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.model.DriversModel;
import pablo.baro.formulaone.ui.main.SectionsPagerAdapter;
import pablo.baro.formulaone.databinding.ActivityDriversTabbedBinding;

public class DriversTabbed extends AppCompatActivity implements DriversFragment1.OnFragmentInteractionListener {

    private ActivityDriversTabbedBinding binding;
    DriverViewModel dvm;
    DriversModel driver;

    static FragmentManager fm;

    static boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDriversTabbedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        fm = getSupportFragmentManager();

        if(findViewById(R.id.driver_add_to_favs_layout)!=null){
            twoPane = true;
        }
    }

    public static boolean getTwoPane(){
        return twoPane;
    }

    public static FragmentManager getSupportFragment(){
        return fm;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public DriversModel getDriver(String name) {
        dvm.getAllDrivers().observe(this, new Observer<List<DriversModel>>() {
            @Override
            public void onChanged(List<DriversModel> driversModels) {
                if (driversModels!=null){
                    for (int i = 0 ; i < driversModels.size();i++){
                        if (driversModels.get(i).getName().equals(name)){
                            driver = driversModels.get(i);
                        }
                    }
                }
            }
        });
        return driver;
    }
}