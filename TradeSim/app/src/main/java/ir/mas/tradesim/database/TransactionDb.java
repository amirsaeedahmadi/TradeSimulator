package ir.mas.tradesim.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.TransactionType;

@Entity(tableName = "transaction_table")
public class TransactionDb {

    @PrimaryKey
    @NonNull
    private int transactionId;
    @TypeConverters(TransactionTypeConverters.class)
    private TransactionType type;
    @TypeConverters(CurrencyConverters.class)
    private Currency currency;
    private double currencyAmount;
    private double rialAmount;

    public TransactionDb(@NonNull int transactionId, TransactionType type, Currency currency, double currencyAmount, double rialAmount) {
        this.transactionId = transactionId;
        this.type = type;
        this.currency = currency;
        this.currencyAmount = currencyAmount;
        this.rialAmount = rialAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NonNull int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public double getRialAmount() {
        return rialAmount;
    }

    public void setRialAmount(double rialAmount) {
        this.rialAmount = rialAmount;
    }
}
