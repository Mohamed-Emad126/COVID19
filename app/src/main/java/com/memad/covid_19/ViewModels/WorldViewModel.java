package com.memad.covid_19.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.memad.covid_19.API.RetrofitAPI;
import com.memad.covid_19.Models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldViewModel extends ViewModel {

    MutableLiveData<List<Country>> countries;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isError = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public LiveData<List<Country>> getAllCountries() {
        if(countries == null){
            countries = new MutableLiveData<>();
            getNewCases();
        }
        return countries;
    }

    private void getNewCases() {
        isError.postValue(false);
        isLoading.postValue(true);
        Call<List<Country>> call = RetrofitAPI.getInstance().getAllCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                isLoading.postValue(false);
                if(response.isSuccessful()){
                    countries.postValue(response.body());
                }
                else{
                    isError.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                isLoading.postValue(false);
                isError.postValue(true);
            }
        });
    }

    public void refresh(){
        getNewCases();
    }

}
