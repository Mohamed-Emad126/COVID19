package com.memad.covid_19.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.memad.covid_19.Adapters.AdvicesAdapter;
import com.memad.covid_19.Models.Advice;
import com.memad.covid_19.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvicesFragment extends Fragment {

    public AdvicesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] images = {
                R.drawable.advice_1, R.drawable.advice_2,
                R.drawable.advice_3, R.drawable.advice_4,
                R.drawable.advice_5, R.drawable.advice_6,
                R.drawable.advice_7, R.drawable.advice_8,
                R.drawable.advice_9, R.drawable.advice_10,
                R.drawable.advice_11, R.drawable.advice_12,
                R.drawable.advice_13, R.drawable.advice_15,
                R.drawable.advice_16, R.drawable.advice_17,
                R.drawable.advice_18
        };
        String[] advices = {
                getString(R.string.advice_1), getString(R.string.advice_2),
                getString(R.string.advice_3), getString(R.string.advice_4),
                getString(R.string.advice_5), getString(R.string.advice_6),
                getString(R.string.advice_7), getString(R.string.advice_8),
                getString(R.string.advice_9), getString(R.string.advice_10),
                getString(R.string.advice_11), getString(R.string.advice_12),
                getString(R.string.advice_13), getString(R.string.advice_15),
                getString(R.string.advice_16), getString(R.string.advice_17),
                getString(R.string.advice_18),

        };
        for (int i = 0; i < advices.length; i++) {
            Advice a = new Advice();
            a.setImage(images[i]);
            a.setText(advices[i]);
            advice.add(a);
        }
    }

    private RecyclerView recyclerView;
    private AdvicesAdapter adapter;
    private List<Advice> advice = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_advices, container, false);
        adapter = new AdvicesAdapter();
        adapter.setAdvicesList(advice);
        recyclerView = rootView.findViewById(R.id.advices_recycler);
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(adapter);


        return rootView;
    }
}
