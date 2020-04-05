package com.memad.covid_19.API;

import com.memad.covid_19.Models.AllCases;
import com.memad.covid_19.Models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface COVIDClient {

    @GET("all")
    Call<AllCases> getAllCases();

    @GET("countries/egy")
    Call<Country> getEgyptCases();

    @GET("countries?sort=totalCases")
    Call<List<Country>> getAllCountries();

}
