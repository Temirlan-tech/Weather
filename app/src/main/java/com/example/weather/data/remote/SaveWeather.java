package com.example.weather.data.remote;

import com.example.weather.data.model.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveWeather {

    public static void getWeather(String city,
                                  String units,
                                  String appId,
                                  GetWeatherByCity getWeatherByCity) {
        RetrofitBuilder
                .getInstance()
                .getWeather(city, units, appId)
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        if (response.isSuccessful() && response.body() != null){
                            getWeatherByCity.onSuccess(response.body());
                        } else {
                            getWeatherByCity.onFailure(response.message() + "if else ");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {
                        getWeatherByCity.onFailure(t.getLocalizedMessage() + "with t.get");
                    }
                });
    }


    public interface GetWeatherByCity {
        void onSuccess(WeatherModel myWeather);
        void onFailure(String error);
    }
}
