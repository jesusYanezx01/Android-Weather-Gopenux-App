package com.example.androidopenweathergopenux.presentation.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidopenweathergopenux.R;
import com.example.androidopenweathergopenux.data.models.WeatherResponse;
import com.example.androidopenweathergopenux.data.repositories.WeatherRepository;
import com.example.androidopenweathergopenux.data.repositories.WeatherRepositoryImpl;
import com.example.androidopenweathergopenux.domain.usecases.GetWeatherDataUseCase;
import com.example.androidopenweathergopenux.presentation.viewmodels.WeatherViewModel;
import com.example.androidopenweathergopenux.presentation.viewmodels.WeatherViewModelFactory;

public class WeatherActivity extends AppCompatActivity {

    private TextView temperatureTextView;
    private TextView feelsLikeTextView;
    private TextView humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        WeatherRepository weatherRepository = new WeatherRepositoryImpl();
        GetWeatherDataUseCase getWeatherDataUseCase = new GetWeatherDataUseCase(weatherRepository);

        WeatherViewModelFactory factory = new WeatherViewModelFactory(getWeatherDataUseCase);
        WeatherViewModel weatherViewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

        TextView cityNameTextView = findViewById(R.id.cityNameTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        feelsLikeTextView = findViewById(R.id.feelsLikeTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        String cityName = getIntent().getStringExtra("cityName");
        cityNameTextView.setText(cityName);

        weatherViewModel.getWeatherData().observe(this, weatherResponse -> {
            if (weatherResponse != null) {
                displayWeatherInfo(weatherResponse);
            }
        });

        weatherViewModel.fetchWeatherData(cityName);
    }

    private void displayWeatherInfo(WeatherResponse weatherResponse) {
        temperatureTextView.setText(getString(R.string.temperature, weatherResponse.getMain().getTemp()));
        feelsLikeTextView.setText(getString(R.string.feels_like, weatherResponse.getMain().getFeelsLike()));
        humidityTextView.setText(getString(R.string.humidity, weatherResponse.getMain().getHumidity()));
    }
}
