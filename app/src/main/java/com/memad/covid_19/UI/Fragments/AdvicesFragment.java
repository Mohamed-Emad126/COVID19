package com.memad.covid_19.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.memad.covid_19.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvicesFragment extends Fragment {

    public AdvicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_symptoms, container, false);
    }
}
