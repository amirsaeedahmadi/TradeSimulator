package ir.mas.tradesim.database;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ir.mas.tradesim.model.Currency;
public class CurrencyConverters {

    @TypeConverter
    public static String fromCurrency(Currency currency) {
        return new Gson().toJson(currency);
    }

    @TypeConverter
    public static Currency fromStringToCurrency(String value) {
        Type type = new TypeToken<Currency>() {}.getType();
        return new Gson().fromJson(value, type);
    }
}
