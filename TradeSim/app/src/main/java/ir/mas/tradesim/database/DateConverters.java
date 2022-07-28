package ir.mas.tradesim.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

public class DateConverters {

    @TypeConverter
    public static String fromDate(Date date) {
        return new Gson().toJson(date);
    }

    @TypeConverter
    public static Date fromStringToDate(String value) {
        Type type = new TypeToken<Date>() {}.getType();
        return new Gson().fromJson(value, type);
    }
}
