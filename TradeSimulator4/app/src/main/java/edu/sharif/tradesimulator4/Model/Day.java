package edu.sharif.tradesimulator4.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Day {

    public String temperature;
    public String nightTemp;
    public String eveningTemp;
    public String morningTemp;
    public String temperature_feels_like;
    public String wind_speed;
    public String weatherIcon;

    public int weatherCondition;

    public static ArrayList<Day> days = new ArrayList<>();

    public static Day fromJson(JSONObject jsonObject) {

        try{

            Day dayItem = new Day();
            double temperatureTemp = jsonObject.getJSONObject("temp").getDouble("day") - 273.15;
            dayItem.temperature = Integer.toString((int) Math.rint(temperatureTemp));
            temperatureTemp = jsonObject.getJSONObject("feels_like").getDouble("day") - 273.15;
            dayItem.temperature_feels_like = Integer.toString((int) Math.rint(temperatureTemp));
            Long wind =  Math.round(jsonObject.getDouble("wind_speed") * 3.6 * 10) / 10;
            dayItem.wind_speed = Long.toString(wind);
            dayItem.weatherCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            temperatureTemp = jsonObject.getJSONObject("temp").getDouble("night") - 273.15;
            dayItem.nightTemp = Integer.toString((int) Math.rint(temperatureTemp));
            temperatureTemp = jsonObject.getJSONObject("temp").getDouble("eve") - 273.15;
            dayItem.eveningTemp = Integer.toString((int) Math.rint(temperatureTemp));
            temperatureTemp = jsonObject.getJSONObject("temp").getDouble("morn") - 273.15;
            dayItem.morningTemp = Integer.toString((int) Math.rint(temperatureTemp));

            dayItem.weatherIcon = setWeatherIcon(dayItem.weatherCondition);

            days.add(dayItem);

            return dayItem;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String setWeatherIcon(int weatherCondition) {


        if (weatherCondition >= 0 && weatherCondition <= 300) {
            return "thunderstorm";
        } else if (weatherCondition >= 300 && weatherCondition <= 500) {
            return "rain";
        } else if (weatherCondition >= 500 && weatherCondition <= 600) {
            return "shower_rain";
        } else if (weatherCondition >= 600 && weatherCondition <= 700) {
            return "snow";
        } else if (weatherCondition >= 701 && weatherCondition <= 799) {
            return "mist";
        } else if (weatherCondition == 800) {
            return "clear_sky";
        } else if (weatherCondition == 801) {
            return "few_clouds";
        } else if (weatherCondition == 802) {
            return "scattered_clouds";
        } else if (weatherCondition >= 803) {
            return "broken_clouds";
        }

        return null;
    }


    public String getTemperature() {
        return temperature + "°C";
    }

    public String getNightTemp() {
        return nightTemp + "°C";
    }

    public String getEveningTemp() {
        return eveningTemp + "°C";
    }

    public String getMorningTemp() {
        return morningTemp + "°C";
    }

    public String getTemperature_feels_like() {
        return temperature_feels_like + "°C";
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }


    public static ArrayList<Day> getDays() {
        return days;
    }
}
