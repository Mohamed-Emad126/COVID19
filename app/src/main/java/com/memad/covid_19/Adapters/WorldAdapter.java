package com.memad.covid_19.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.memad.covid_19.Models.Country;
import com.memad.covid_19.R;

import java.util.List;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.WorldViewHolder> {

    List<Country> CountrysList;

    public WorldAdapter() {
    }

    public void clearAll() {
        if (CountrysList != null) {
            this.CountrysList.clear();
            notifyDataSetChanged();
        }
    }

    public void setCountriesList(List<Country> CountrysList) {
        if (this.CountrysList == null) {
            this.CountrysList = CountrysList;
            notifyDataSetChanged();
        } else {
            int lastFinish = this.CountrysList.size() - 1;
            this.CountrysList.addAll(CountrysList);
            notifyItemInserted(lastFinish);
            //notifyItemRangeInserted(lastFinish, newFinish);
        }

    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public WorldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country, parent, false);
        return new WorldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorldViewHolder holder, int position) {
        holder.titleChip.setText(CountrysList.get(position).getCountry());
        holder.totalCases.setText(String.valueOf(CountrysList.get(position).getCases()));
        holder.todayCases.setText(String.valueOf(CountrysList.get(position).getTodayCases()));
        holder.todayDeaths.setText(String.valueOf(CountrysList.get(position).getTodayDeaths()));
        holder.totalDeaths.setText(String.valueOf(CountrysList.get(position).getDeaths()));
        holder.recovered.setText(String.valueOf(CountrysList.get(position).getRecovered()));
    }

    @Override
    public int getItemCount() {
        return (CountrysList == null) ? 0 : CountrysList.size();
    }


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////

    public class WorldViewHolder extends RecyclerView.ViewHolder {

        Chip titleChip;

        Chip totalCases;
        Chip todayCases;
        Chip todayDeaths;
        Chip totalDeaths;
        Chip recovered;

        public WorldViewHolder(@NonNull View itemView) {
            super(itemView);

            titleChip = itemView.findViewById(R.id.title_chip);
            totalCases = itemView.findViewById(R.id.total_cases_world);
            todayCases = itemView.findViewById(R.id.today_cases_world);
            todayDeaths = itemView.findViewById(R.id.today_deaths_world);
            totalDeaths = itemView.findViewById(R.id.total_deaths_world);
            recovered = itemView.findViewById(R.id.recovered_world);


        }
    }

}