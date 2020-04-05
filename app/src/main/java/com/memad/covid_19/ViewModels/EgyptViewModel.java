package com.memad.covid_19.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.memad.covid_19.API.RetrofitAPI;
import com.memad.covid_19.Models.Country;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EgyptViewModel extends ViewModel {
    MutableLiveData<Country> egypt;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Boolean> isError = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public LiveData<Country> getEgypt() {
        if(egypt == null){
            egypt = new MutableLiveData<>();
            getNewCases();
        }
        return egypt;
    }

    private void getNewCases() {
        isError.postValue(false);
        isLoading.postValue(true);
        Call<Country> call = RetrofitAPI.getInstance().getEgyptCases();
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                isLoading.postValue(false);
                if(response.isSuccessful()){
                    egypt.postValue(response.body());
                }
                else{
                    isError.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                isError.postValue(true);
                isLoading.postValue(false);
            }
        });
    }

    public void refresh(){
        getNewCases();
    }

}
