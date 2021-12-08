package pablo.baro.formulaone.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pablo.baro.formulaone.model.DriversModel;

@Dao
public interface FavDriversDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DriversModel model);

    @Delete
    void deleteWord(DriversModel model);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<DriversModel>> getAllWords();
}
