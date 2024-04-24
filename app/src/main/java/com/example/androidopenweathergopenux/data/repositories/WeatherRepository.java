package com.example.androidopenweathergopenux.data.repositories;

public interface WeatherRepository {
    void getWeatherData(String cityName, WeatherCallback callback);
}
