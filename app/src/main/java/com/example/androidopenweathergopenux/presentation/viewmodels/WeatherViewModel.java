package com.example.androidopenweathergopenux.presentation.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidopenweathergopenux.data.models.WeatherResponse;
import com.example.androidopenweathergopenux.data.repositories.WeatherCallback;
import com.example.androidopenweathergopenux.domain.usecases.GetWeatherDataUseCase;

public class WeatherViewModel extends ViewModel {

    private final GetWeatherDataUseCase getWeatherDataUseCase;

    private MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public WeatherViewModel(GetWeatherDataUseCase getWeatherDataUseCase) {
        this.getWeatherDataUseCase = getWeatherDataUseCase;
    }



    public LiveData<WeatherResponse> getWeatherData() {
        return weatherData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchWeatherData(String cityName) {
        getWeatherDataUseCase.execute(cityName, new WeatherCallback() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                weatherData.setValue(weatherResponse);
            }

            @Override
            public void onError(String message) {
                errorMessage.setValue(message);
            }
        });
    }
}
