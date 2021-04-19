package com.example.weather.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private RetrofitBuilder(){
    }

    private static WeatherApi instance;

    public static WeatherApi getInstance() {
        if (instance == null) {
            instance = createApi();
        }
        return instance;
    }

    private static WeatherApi createApi() {
        return new Retrofit
                .Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi.class);
    }
}
