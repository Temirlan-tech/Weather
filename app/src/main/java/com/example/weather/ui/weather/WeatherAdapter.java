package com.example.weather.ui.weather;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.databinding.ListItemBinding;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private List<Integer> listImage = List.of(
            R.drawable.ic_sun,
            R.drawable.ic_cloudy,
            R.drawable.ic_cloudy_2,
            R.drawable.ic_rainy,
            R.drawable.ic_stormy,
            R.drawable.ic_sun,
            R.drawable.ic_sun,
            R.drawable.ic_cloudy,
            R.drawable.ic_sun,
            R.drawable.ic_sun);

    private List <String> listDays = List.of("ЧТ", "ПТ", "СБ", "ВС", "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ");

    private List <String> listCelsius = List.of("+25", "+21", "+19", "+19", "+17", "+13", "+16","+18" ,"+23" ,"+28");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding ui = ListItemBinding.inflate(inflater,parent,false);
        return new ViewHolder(ui);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemBinding ui;

        public ViewHolder(@NonNull ListItemBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
        }

        public void onBind(int position) {
            ui.days.setText(listDays.get(position));
            ui.cerc.setText(listCelsius.get(position));
            ui.imageView.setImageResource(listImage.get(position));
        }
    }
}
