package com.example.androidopenweathergopenux.data.remote;

import com.example.androidopenweathergopenux.data.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather")
    Call<WeatherResponse> getWeatherData(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );
}
