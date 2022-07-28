package ir.mas.tradesim.database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ir.mas.tradesim.model.Transaction;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transaction_table")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("DELETE FROM transaction_table")
    void deleteTransactions();
}
