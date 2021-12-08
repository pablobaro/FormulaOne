package pablo.baro.formulaone;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pablo.baro.formulaone.adapters.DriverAdapter;
import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.ui.main.SectionsPagerAdapter;
import pablo.baro.formulaone.databinding.ActivityDriversTabbedBinding;

public class DriversTabbed extends AppCompatActivity {

    private ActivityDriversTabbedBinding binding;
    DriverViewModel dvm;
    Button starButton;

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
    }
}