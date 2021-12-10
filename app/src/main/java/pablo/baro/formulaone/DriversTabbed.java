package pablo.baro.formulaone;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import java.sql.Driver;

import pablo.baro.formulaone.controllers.DriverViewModel;
import pablo.baro.formulaone.ui.main.SectionsPagerAdapter;
import pablo.baro.formulaone.databinding.ActivityDriversTabbedBinding;

public class DriversTabbed extends AppCompatActivity {

    private ActivityDriversTabbedBinding binding;
    DriverViewModel dvm;
    Button starButton;
    static boolean mTwoPane = false;
    static FragmentManager fm;
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

        if(findViewById(R.id.driver_add_to_favs_layout)!=null){
            mTwoPane = true;
        }
        getAct();
    }

    public static boolean getTwoPane(){
        return mTwoPane;
    }

    public static FragmentManager getFragmet(){
        return fm;
    }

    public FragmentManager getAct(){
        fm = getSupportFragmentManager();
        return fm;
    }
}