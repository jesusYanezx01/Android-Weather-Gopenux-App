package com.example.androidopenweathergopenux.data.models;

import com.example.androidopenweathergopenux.utils.TemperatureFormatter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherResponse implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("main")
    private Main main;

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }


    public static class Main implements Serializable {
        @SerializedName("temp")
        private double temp;

        @SerializedName("feels_like")
        private double feelsLike;

        @SerializedName("humidity")
        private String humidity;

        public String getTemp() {
            return TemperatureFormatter.format(temp);
        }

        public String getFeelsLike() {
            return TemperatureFormatter.format(feelsLike);
        }

        public String getHumidity() {
            return humidity;
        }
    }
}
