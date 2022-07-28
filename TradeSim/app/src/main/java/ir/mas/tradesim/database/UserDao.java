package ir.mas.tradesim.database;




import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserDb user);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserDb>> getAllUsers();

    @Query("DELETE FROM user_table")
    void deleteUsers();
}
