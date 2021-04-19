package com.example.weather.ui.location;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weather.R;
import com.example.weather.databinding.FragmentLocationBinding;
import com.example.weather.databinding.FragmentWeatherBinding;
import com.example.weather.ui.weather.WeatherAdapter;

import java.util.List;

public class LocationFragment extends Fragment {

    public LocationFragment(){

    }
    private FragmentLocationBinding fragmentLocationBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLocationBinding = FragmentLocationBinding.inflate(getLayoutInflater());
        return fragmentLocationBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        List<String> listCity = List.of( "Bishkek", "New York",
                "London", "Paris",
                "Istanbul", "Almaty",
                "Kyiv", "Seoul",
                "Tokyo", "Osaka",
                "Beijing", "Oslo",
                "Moscow", "Miami",
                "Montreal", "Munich",
                "Humburg", "Dublin",
                "Vienna", "Lyon",
                "Muscat", "Manila",
                "Tokyo", "Dubai",
                "Barcelona", "Rome",
                "Chicago", "Toronto");

        fragmentLocationBinding.rvLocation.setAdapter(new LocationAdapter(listCity, city -> {
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            NavHostFragment navHostFragment =
                    (NavHostFragment)   requireActivity()
                            .getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            navController.navigate(R.id.startFragment, bundle);

        }));
    }


}