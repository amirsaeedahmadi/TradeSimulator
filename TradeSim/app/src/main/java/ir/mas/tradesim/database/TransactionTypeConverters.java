package ir.mas.tradesim.database;


import androidx.room.TypeConverter;

import ir.mas.tradesim.model.TransactionType;


public class TransactionTypeConverters {

    @TypeConverter
    public static String fromTransactionType(TransactionType value) {
        return value.toString();
    }

    @TypeConverter
    public static TransactionType fromStringToTransactionType(String value) {
        return TransactionType.valueOf(value);
    }

}