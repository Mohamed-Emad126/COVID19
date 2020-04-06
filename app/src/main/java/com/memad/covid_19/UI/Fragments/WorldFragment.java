package com.memad.covid_19.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.memad.covid_19.Adapters.WorldAdapter;
import com.memad.covid_19.R;
import com.memad.covid_19.Utils.NetworkUtils;
import com.memad.covid_19.ViewModels.WorldViewModel;

public class WorldFragment extends Fragment {

    public WorldFragment() {
        // Required empty public constructor
    }

    private WorldViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_world, container, false);
        SwipeRefreshLayout refreshLayout = rootView.findViewById(R.id.refresh);
        TextView totalCases = rootView.findViewById(R.id.total_world_cases_num);
        TextView totalDeaths = rootView.findViewById(R.id.total_world_death_num);
        TextView recovered = rootView.findViewById(R.id.total_world_recovered_num);
        FrameLayout progressBar = rootView.findViewById(R.id.progressBar);

        MaterialButton networkRefresh = rootView.findViewById(R.id.no_network_refresh);
        MaterialButton errorPageRefresh = rootView.findViewById(R.id.error_page_refresh);

        LinearLayout errorPage = rootView.findViewById(R.id.error_linear_layout);
        LinearLayout noNetworkPage = rootView.findViewById(R.id.network_linear_layout);

        FragmentActivity activity = getActivity();

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        WorldAdapter adapter = new WorldAdapter();
        recyclerView.setSaveEnabled(true);
        recyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(requireActivity()).get(WorldViewModel.class);

        viewModel.getAllCases();
        viewModel.getAllCountries();


        errorPageRefresh.setOnClickListener(view -> {
            viewModel.setIsFirstLoading(true);
            viewModel.refresh();
        });
        networkRefresh.setOnClickListener(view -> {
            viewModel.setIsFirstLoading(true);
            viewModel.refresh();
        });


        if (NetworkUtils.getConnectivityStatus(
                requireActivity().getApplicationContext()) == 0) {
            noNetworkPage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            errorPage.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }


        viewModel.getAllCountries().observe(requireActivity(), countries -> {
                    adapter.setCountriesList(countries);
                    noNetworkPage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    errorPage.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
        );

        viewModel.getIsFirstLoading().observe(requireActivity(), aBoolean -> {
            if (aBoolean) {
                refreshLayout.setRefreshing(false);
                noNetworkPage.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });


        viewModel.getAllCases().observe(requireActivity(), allCases -> {
                    totalCases.setText(String.valueOf(allCases.getCases()));
                    totalDeaths.setText(String.valueOf(allCases.getDeaths()));
                    recovered.setText(String.valueOf(allCases.getRecovered()));
                }
        );

        viewModel.getIsLoading().observe(requireActivity(), aBoolean -> {
            if (aBoolean) {
                if (viewModel.getIsFirstLoading().getValue() != null &&
                        !viewModel.getIsFirstLoading().getValue()) {
                    refreshLayout.setRefreshing(true);
                }
            } else {
                refreshLayout.setRefreshing(false);
            }
        });

        viewModel.getIsError().observe(requireActivity(), aBoolean -> {
            if (aBoolean) {
                refreshLayout.setRefreshing(false);
                totalCases.setText("-");
                totalDeaths.setText("-");
                recovered.setText("-");
                if (NetworkUtils.getConnectivityStatus(
                        activity) != 0) {

                    noNetworkPage.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    errorPage.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    /*Toast.makeText(requireActivity().getApplicationContext(),
                            R.string.wrong, Toast.LENGTH_SHORT).show();*/

                } else {
                    noNetworkPage.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    errorPage.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    /*Toast.makeText(requireActivity().getApplicationContext(),
                            R.string.network_connection, Toast.LENGTH_SHORT).show();*/
                }
            }
        });

        refreshLayout.setOnRefreshListener(() -> {
            if (NetworkUtils.getConnectivityStatus(
                    activity.getApplicationContext()) != 0) {
                viewModel.refresh();
            } else {
                Toast.makeText(requireActivity().getApplicationContext(),
                        R.string.update_no_connection, Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });


        return rootView;
    }
}
