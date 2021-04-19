package com.example.weather.ui.weather;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weather.R;
import com.example.weather.data.model.Weather;
import com.example.weather.data.model.WeatherModel;
import com.example.weather.data.remote.SaveWeather;
import com.example.weather.databinding.FragmentWeatherBinding;
import com.example.weather.ui.weather.WeatherAdapter;

import java.util.ArrayList;
import java.util.Date;

public class WeatherFragment extends Fragment {

    private WeatherAdapter weatherAdapter;
    private FragmentWeatherBinding bind;
    private String city;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString("city");
        }
        weatherAdapter = new WeatherAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentWeatherBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getWeatherData();

    }

    private void getWeatherData() {
        SaveWeather.getWeather(city,
                "metric",
                "4bbf5a1ed98cd9f688ebb3651474cdd2",
                new SaveWeather.GetWeatherByCity() {
                    @Override
                    public void onSuccess(WeatherModel weatherModel) {
                        setWeatherData(weatherModel);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
    }

    private void setWeatherData(WeatherModel weatherModel) {
        String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
        bind.txtViewDate.setText(date);

        String cityName = weatherModel.getName();
        String countryName = weatherModel.getSys().getCountry();
        bind.txtViewCity.setText(cityName + " " + countryName + "  ");

        ArrayList<Weather> cloud = (ArrayList<Weather>) weatherModel.getWeather();
        Weather weather = cloud.get(0);
        bind.txtViewData.setText(String.valueOf(weather.getMain()));

        String iconCode = weather.getIcon();
        String iconUrl = "http://openweathermap.org/img/w/" + iconCode + ".png";
        Glide.with(getActivity()).load(iconUrl).into(bind.imageViewIcon);

        Double fahrenheit = weatherModel.getMain().getTemp();
        bind.txtViewCelsius.setText(String.valueOf(fahrenheit));

//        Double fahrenheitUp = weatherModel.getMain().getTempMax();
//        ui.textCelsiusUp.setText(String.valueOf(fahrenheitUp));
//
//        Double fahrenheitDown = weatherModel.getMain().getTempMin();
//        ui.textCelsiusDown.setText(String.valueOf(fahrenheitDown));

        Integer humidity = weatherModel.getMain().getHumidity();
        bind.txtViewHumidity2.setText(humidity + "%");

        Integer pressure = weatherModel.getMain().getPressure();
        bind.txtViewPressure2.setText(pressure + "mBar");

        Double wind = weatherModel.getWind().getSpeed();
        bind.txtViewWind2.setText(wind + "km/h");

        int sunriseSecs = weatherModel.getSys().getSunrise();
        @SuppressLint("SimpleDateFormat")
        String dateRise = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date(sunriseSecs * 1000));
        bind.txtViewSunrice2.setText(dateRise);

        int sunsetSecs = weatherModel.getSys().getSunset();
        @SuppressLint("SimpleDateFormat")
        String dateSet = new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date(sunsetSecs * 1000));
        bind.txtViewSunset2.setText(dateSet);
    }

    private void init() {
        bind.rvDays.setAdapter(weatherAdapter);
        bind.txtViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);
                assert navHostFragment != null;
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.locationFragment);
            }
        });
    }

}