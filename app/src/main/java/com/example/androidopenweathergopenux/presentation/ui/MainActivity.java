package com.example.androidopenweathergopenux.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidopenweathergopenux.R;
import com.example.androidopenweathergopenux.data.repositories.WeatherRepository;
import com.example.androidopenweathergopenux.data.repositories.WeatherRepositoryImpl;
import com.example.androidopenweathergopenux.domain.usecases.GetWeatherDataUseCase;
import com.example.androidopenweathergopenux.presentation.viewmodels.WeatherViewModel;
import com.example.androidopenweathergopenux.presentation.viewmodels.WeatherViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;
    private EditText cityEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.cityEditText);
        searchButton = findViewById(R.id.searchButton);

        WeatherRepository weatherRepository = new WeatherRepositoryImpl();
        GetWeatherDataUseCase getWeatherDataUseCase = new GetWeatherDataUseCase(weatherRepository);

        WeatherViewModelFactory factory = new WeatherViewModelFactory(getWeatherDataUseCase);
        weatherViewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityEditText.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    weatherViewModel.fetchWeatherData(cityName);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        weatherViewModel.getWeatherData().observe(this, weatherResponse -> {
            if (weatherResponse != null) {
                navigateToWeatherActivity(weatherResponse.getName());
            }
        });

        weatherViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToWeatherActivity(String cityName) {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }
}