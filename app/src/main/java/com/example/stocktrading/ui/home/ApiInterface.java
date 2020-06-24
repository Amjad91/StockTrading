package com.example.stocktrading.ui.home;

import com.example.stocktrading.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("WIKI/{symbol}.json?api_key="+BuildConfig.CHART_API_KEY)
    Call<Chart> init(@Path("symbol") String symbol);
}
