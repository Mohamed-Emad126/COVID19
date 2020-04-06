package com.memad.covid_19.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memad.covid_19.Models.Advice;
import com.memad.covid_19.R;

import java.util.List;

public class AdvicesAdapter extends RecyclerView.Adapter<AdvicesAdapter.AdviceViewHolder> {

    List<Advice> advicesList;

    public AdvicesAdapter() {
    }


    public void setAdvicesList(List<Advice> AdvicesList) {
        this.advicesList = AdvicesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advice, parent, false);
        return new AdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
        holder.adviceImage.setImageResource(advicesList.get(position).getImage());
        holder.adviceText.setText(advicesList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return (advicesList == null) ? 0 : advicesList.size();
    }


    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////

    public class AdviceViewHolder extends RecyclerView.ViewHolder {

        ImageView adviceImage;
        TextView adviceText;

        public AdviceViewHolder(@NonNull View itemView) {
            super(itemView);

            adviceImage = itemView.findViewById(R.id.advice_image);
            adviceText = itemView.findViewById(R.id.advice_text);


        }
    }

}