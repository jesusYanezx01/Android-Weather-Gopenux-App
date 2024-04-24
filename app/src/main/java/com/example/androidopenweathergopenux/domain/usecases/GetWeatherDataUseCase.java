package com.example.androidopenweathergopenux.domain.usecases;

import com.example.androidopenweathergopenux.data.models.WeatherResponse;
import com.example.androidopenweathergopenux.data.repositories.WeatherCallback;
import com.example.androidopenweathergopenux.data.repositories.WeatherRepository;

public class GetWeatherDataUseCase {
    private final WeatherRepository weatherRepository;

    public GetWeatherDataUseCase(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void execute(String cityName, WeatherCallback callback) {
        weatherRepository.getWeatherData(cityName, new WeatherCallback() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                callback.onSuccess(weatherResponse);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
