package com.example.weather.data.remote;

import com.example.weather.data.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?")
    Call<WeatherModel> getWeather(
            @Query("q") String city,
            @Query("units") String units,
            @Query("appid") String appId);
}
