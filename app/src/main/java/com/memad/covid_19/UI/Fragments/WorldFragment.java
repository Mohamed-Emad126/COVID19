package com.memad.covid_19.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.memad.covid_19.Adapters.WorldAdapter;
import com.memad.covid_19.R;
import com.memad.covid_19.ViewModels.WorldViewModel;

public class WorldFragment extends Fragment {

    public WorldFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_world, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        WorldViewModel viewModel = new ViewModelProvider(requireActivity()).get(WorldViewModel.class);

        WorldAdapter adapter = new WorldAdapter();
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(adapter);

        viewModel.getAllCountries().observe(requireActivity(), adapter::setCountriesList
        );



        return rootView;
    }
}
