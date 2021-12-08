package pablo.baro.formulaone.controllers;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pablo.baro.formulaone.DAO.FavDriversDAO;
import pablo.baro.formulaone.model.DriverRoomDatabase;
import pablo.baro.formulaone.model.DriversModel;

public class DriverRepository {
    private FavDriversDAO dao;
    private LiveData<List<DriversModel>> mAllDrivers;

    DriverRepository(Application application){
        DriverRoomDatabase db = DriverRoomDatabase.getDatabase(application);
        dao = db.driverDao();
        mAllDrivers = dao.getAllDrivers();
    }

    LiveData<List<DriversModel>> getAllDrivers() {
        return mAllDrivers;
    }

    public void insert(DriversModel driver) {
        new insertAsyncTask(dao).execute(driver);
    }

    public void deleteWord(DriversModel driver) {
        new deleteWordAsyncTask(dao).execute(driver);
    }

    private static class insertAsyncTask extends AsyncTask<DriversModel, Void, Void> {

        private FavDriversDAO mAsyncTaskDao;

        insertAsyncTask(FavDriversDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DriversModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<DriversModel, Void, Void> {
        private FavDriversDAO mAsyncTaskDao;

        deleteWordAsyncTask(FavDriversDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DriversModel... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}