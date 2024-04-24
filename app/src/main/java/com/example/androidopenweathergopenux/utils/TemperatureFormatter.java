package com.example.androidopenweathergopenux.utils;

import java.text.DecimalFormat;

public class TemperatureFormatter {
    public static String format(double temperature) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(temperature - 273.15);
    }
}
