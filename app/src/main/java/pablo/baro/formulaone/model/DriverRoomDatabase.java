package pablo.baro.formulaone.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pablo.baro.formulaone.DAO.FavDriversDAO;

@Database(entities = {DriversModel.class}, version = 2, exportSchema = false)
public abstract class DriverRoomDatabase extends RoomDatabase{
    public abstract FavDriversDAO driverDao();

    private static DriverRoomDatabase INSTANCE;

    public static DriverRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DriverRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DriverRoomDatabase.class, "driver_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static DriverRoomDatabase.Callback sRoomDatabaseCallback =
            new DriverRoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final FavDriversDAO mDao;

        //private static String [] words = {"dolphin🐬", "crocodile🐊", "cobra🐍"};

        PopulateDbAsync(DriverRoomDatabase db) {
            mDao = db.driverDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            /*if (mDao.getAnyWord().length < 1) {
                for (int i = 0; i <= words.length - 1; i++) {
                    DriversModel word = new DriversModel(words[i]);
                    mDao.insert(word);
                }
            }*/
            return null;
        }
    }
}
