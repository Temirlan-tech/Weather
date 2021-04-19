package com.example.weather.ui.location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.databinding.ListItemBinding;
import com.example.weather.databinding.ListItemLocationBinding;
import com.example.weather.ui.OnItemClickListener;
import com.example.weather.ui.weather.WeatherAdapter;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private final List<String> cities;
    private OnItemClickListener clickListener;


    public LocationAdapter(List<String> cities, OnItemClickListener clickListener) {
        this.cities = cities;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemLocationBinding ui = ListItemLocationBinding.inflate(inflater,parent,false);
        return new LocationAdapter.ViewHolder(ui);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemLocationBinding ui;

        public ViewHolder(@NonNull ListItemLocationBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
            ui.getRoot().setOnClickListener(v -> clickListener.cityClick(cities.get(getAdapterPosition())));

        }

        public void onBind(int position) {
            ui.txtLocation.setText(cities.get(position));
        }
    }
}
