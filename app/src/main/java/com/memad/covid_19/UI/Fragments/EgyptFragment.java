package com.memad.covid_19.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.memad.covid_19.R;
import com.memad.covid_19.Utils.NetworkUtils;
import com.memad.covid_19.ViewModels.EgyptViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EgyptFragment extends Fragment {

    public EgyptFragment() {
        // Required empty public constructor
    }

    private EgyptViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_egypt, container, false);

        TextView totalCases = rootView.findViewById(R.id.total_cases_num);
        TextView totalDeaths = rootView.findViewById(R.id.total_deaths_num);
        TextView todayCases = rootView.findViewById(R.id.today_cases_num);
        TextView todayDeaths = rootView.findViewById(R.id.today_deaths_num);
        TextView recovered = rootView.findViewById(R.id.recovered_num);
        TextView critical = rootView.findViewById(R.id.critical_num);
        TextView active = rootView.findViewById(R.id.active_num);
        TextView casesPerOneMillion = rootView.findViewById(R.id.casesPerOneMillion);
        TextView deathsPerOneMillion = rootView.findViewById(R.id.deathsPerOneMillion);

        SwipeRefreshLayout refreshLayout = rootView.findViewById(R.id.swipe_refresh);

        viewModel = new ViewModelProvider(requireActivity()).get(EgyptViewModel.class);
        viewModel.getEgypt();

        /*if(NetworkUtils.getConnectivityStatus(requireActivity().getApplicationContext()) != 0)
        {
            viewModel.getEgypt().observe(requireActivity(), country -> {
                totalCases.setText(String.valueOf(country.getCases()));
                todayCases.setText(String.valueOf(country.getTodayCases()));
                totalDeaths.setText(String.valueOf(country.getDeaths()));
                todayDeaths.setText(String.valueOf(country.getTodayDeaths()));
                deathsPerOneMillion.setText(String.valueOf(country.getDeathsPerOneMillion()));
                casesPerOneMillion.setText(String.valueOf(country.getCasesPerOneMillion()));
                recovered.setText(String.valueOf(country.getRecovered()));
                active.setText(String.valueOf(country.getActive()));
                critical.setText(String.valueOf(country.getCritical()));

                    }
            );
        }
        else
            {
            refreshLayout.setRefreshing(false);
            totalCases.setText("-");
            todayCases.setText("-");
            totalDeaths.setText("-");
            todayDeaths.setText("-");
            deathsPerOneMillion.setText("-");
            casesPerOneMillion.setText("-");
            recovered.setText("-");
            active.setText("-");
            critical.setText("-");
            Toast.makeText(requireActivity().getApplicationContext(), R.string.network_connection, Toast.LENGTH_SHORT).show();
        }*/

        viewModel.getEgypt().observe(requireActivity(), country -> {
                    totalCases.setText(String.valueOf(country.getCases()));
                    todayCases.setText(String.valueOf(country.getTodayCases()));
                    totalDeaths.setText(String.valueOf(country.getDeaths()));
                    todayDeaths.setText(String.valueOf(country.getTodayDeaths()));
                    deathsPerOneMillion.setText(String.valueOf(country.getDeathsPerOneMillion()));
                    casesPerOneMillion.setText(String.valueOf(country.getCasesPerOneMillion()));
                    recovered.setText(String.valueOf(country.getRecovered()));
                    active.setText(String.valueOf(country.getActive()));
                    critical.setText(String.valueOf(country.getCritical()));

                }
        );

        viewModel.getIsLoading().observe(requireActivity(), aBoolean -> {
            if(aBoolean){
                refreshLayout.setRefreshing(true);
            }
            else{
                refreshLayout.setRefreshing(false);
            }
        });

        viewModel.getIsError().observe(requireActivity(), aBoolean -> {
            if(aBoolean){
                refreshLayout.setRefreshing(false);
                totalCases.setText("-");
                todayCases.setText("-");
                totalDeaths.setText("-");
                todayDeaths.setText("-");
                deathsPerOneMillion.setText("-");
                casesPerOneMillion.setText("-");
                recovered.setText("-");
                active.setText("-");
                critical.setText("-");
                if(NetworkUtils.getConnectivityStatus(
                        requireActivity()) != 0){

                    Toast.makeText(requireActivity().getApplicationContext(),
                            R.string.wrong, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(requireActivity().getApplicationContext(),
                            R.string.network_connection, Toast.LENGTH_SHORT).show();
                }
            }
        });

        refreshLayout.setOnRefreshListener(() -> {
            if(NetworkUtils.getConnectivityStatus(
                    requireActivity().getApplicationContext()) != 0){
                viewModel.refresh();
            }
            else{
                Toast.makeText(requireActivity().getApplicationContext(),
                        R.string.network_connection, Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }
}
