package pablo.baro.formulaone.controllers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pablo.baro.formulaone.model.DriversModel;

public class DriverViewModel extends AndroidViewModel {
    private DriverRepository repository;
    private LiveData<List<DriversModel>> mAllDrivers;

    public DriverViewModel(@NonNull Application application) {
        super(application);
        repository = new DriverRepository(application);
        mAllDrivers = repository.getAllDrivers();
    }

    public LiveData<List<DriversModel>> getAllDrivers() {
        return mAllDrivers;
    }

    public void insert(DriversModel driver) {
        repository.insert(driver);
    }

    public void deleteDriver(DriversModel driver) {
        repository.deleteDriver(driver);
    }
}