package pablo.baro.formulaone.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import pablo.baro.formulaone.DriversTabbed;


public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(DriversTabbed driver) {
        super(driver);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
