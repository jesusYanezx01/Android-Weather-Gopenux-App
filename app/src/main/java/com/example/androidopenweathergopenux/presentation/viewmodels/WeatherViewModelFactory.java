package com.example.androidopenweathergopenux.presentation.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidopenweathergopenux.domain.usecases.GetWeatherDataUseCase;

public class WeatherViewModelFactory implements ViewModelProvider.Factory {
    private final GetWeatherDataUseCase getWeatherDataUseCase;

    public WeatherViewModelFactory(GetWeatherDataUseCase getWeatherDataUseCase) {
        this.getWeatherDataUseCase = getWeatherDataUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WeatherViewModel.class)) {
            return (T) new WeatherViewModel(getWeatherDataUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
