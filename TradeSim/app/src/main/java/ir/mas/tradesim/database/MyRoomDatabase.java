package ir.mas.tradesim.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@TypeConverters({TransactionTypeConverters.class, CurrencyConverters.class, DateConverters.class})
@Database(entities = {UserDb.class}, version = 5, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    //public abstract TransactionDao transactionDao();

    private static MyRoomDatabase instance;

    public static MyRoomDatabase getInstance(Context context){
        if (instance != null) return instance;
        synchronized (MyRoomDatabase.class){
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        MyRoomDatabase.class,
                        "local_database").fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}
