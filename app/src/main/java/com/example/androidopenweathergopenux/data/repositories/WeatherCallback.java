package com.example.androidopenweathergopenux.data.repositories;

import com.example.androidopenweathergopenux.data.models.WeatherResponse;

public interface WeatherCallback {
    void onSuccess(WeatherResponse weatherResponse);
    void onError(String errorMessage);
}
