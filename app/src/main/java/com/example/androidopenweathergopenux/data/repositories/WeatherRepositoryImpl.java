package com.example.androidopenweathergopenux.data.repositories;

import androidx.annotation.NonNull;

import com.example.androidopenweathergopenux.data.models.WeatherResponse;
import com.example.androidopenweathergopenux.data.remote.RetrofitClient;
import com.example.androidopenweathergopenux.data.remote.WeatherApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApiService weatherApi;

    public WeatherRepositoryImpl() {
        this.weatherApi = RetrofitClient.getClient().create(WeatherApiService.class);
    }

    @Override
    public void getWeatherData(String cityName, WeatherCallback callback) {
        String apiKey = "78ebb3e23dc5e3a2e220bd57027d4384";

        Call<WeatherResponse> call = weatherApi.getWeatherData(cityName, apiKey);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get weather data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }
}
