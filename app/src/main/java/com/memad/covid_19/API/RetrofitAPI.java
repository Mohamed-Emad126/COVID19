package com.memad.covid_19.API;

import com.memad.covid_19.Models.AllCases;
import com.memad.covid_19.Models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    /**
     * <a href="https://iconscout.com/icons/health" target="_blank">Health Icon</a>
     * by <a href="https://iconscout.com/contributors/jemismali">Jemis Mali</a> on
     * <a href="https://iconscout.com">Iconscout</a>
     */
    private static RetrofitAPI ourInstance;
    private static Retrofit base;
    private static COVIDClient covidClient;

    public static RetrofitAPI getInstance() {
        if(ourInstance == null){
            ourInstance = new RetrofitAPI();
        }
        return ourInstance;
    }

    private RetrofitAPI() {
        base = new Retrofit.Builder()
                            .baseUrl("https://corona.lmao.ninja")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        covidClient = base.create(COVIDClient.class);
    }

    public Call<Country> getEgyptCases(){
        return covidClient.getEgyptCases();
    }

    public Call<List<Country>> getAllCountries(){
        return covidClient.getAllCountries();
    }

    public Call<AllCases> getAllCases(){
        return covidClient.getAllCases();
    }

}
