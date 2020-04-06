package com.memad.covid_19.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.memad.covid_19.API.RetrofitAPI;
import com.memad.covid_19.Models.AllCases;
import com.memad.covid_19.Models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldViewModel extends ViewModel {

    MutableLiveData<List<Country>> countries;
    MutableLiveData<AllCases> allCases;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isFirstLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isError = new MutableLiveData<>();

    public void setIsFirstLoading(Boolean isFirstLoading) {
        this.isFirstLoading.postValue(isFirstLoading);
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public LiveData<Boolean> getIsFirstLoading() {
        return isFirstLoading;
    }

    public LiveData<AllCases> getAllCases() {
        if (allCases == null) {
            isFirstLoading.postValue(true);
            allCases = new MutableLiveData<>();
            getWorldCases();
        }
        return allCases;
    }

    private void getWorldCases() {
        isError.postValue(false);
        isLoading.postValue(true);
        Call<AllCases> call = RetrofitAPI.getInstance().getAllCases();
        call.enqueue(new Callback<AllCases>() {
            @Override
            public void onResponse(Call<AllCases> call, Response<AllCases> response) {
                isLoading.postValue(false);
                isFirstLoading.postValue(false);
                if (response.isSuccessful()) {
                    allCases.postValue(response.body());
                } else {
                    isError.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<AllCases> call, Throwable t) {
                isFirstLoading.postValue(false);
                isLoading.postValue(false);
                isError.postValue(true);
            }
        });
    }

    public LiveData<List<Country>> getAllCountries() {
        if (countries == null) {
            isFirstLoading.postValue(true);
            countries = new MutableLiveData<>();
            getCountriesCases();
        }
        return countries;
    }

    private void getCountriesCases() {
        isError.postValue(false);
        isLoading.postValue(true);
        Call<List<Country>> call = RetrofitAPI.getInstance().getAllCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                isLoading.postValue(false);
                isFirstLoading.postValue(false);
                if (response.isSuccessful()) {
                    countries.postValue(response.body());
                } else {
                    isError.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                isFirstLoading.postValue(false);
                isLoading.postValue(false);
                isError.postValue(true);
            }
        });
    }

    public void refresh() {
        getWorldCases();
        getCountriesCases();
    }

}
