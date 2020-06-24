package com.example.stocktrading.ui.home;


import com.example.stocktrading.BuildConfig;
import com.example.stocktrading.ui.NewsPage.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/";

    @Headers({ "x-rapidapi-host: apidojo-yahoo-finance-v1.p.rapidapi.com",
            "x-rapidapi-key:" + BuildConfig.API_KEY
           })
    @GET("news/list?category=generalnews&region=US")
    Call<News> getNews();

    @Headers({ "x-rapidapi-host: apidojo-yahoo-finance-v1.p.rapidapi.com",
            "x-rapidapi-key:" + BuildConfig.API_KEY
    })
    @GET("stock/v2/get-profile")
    Call<Stock> getStock(@Query("symbol") String symbol);


}
