package com.example.stocktrading.ui.NewsPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocktrading.R;
import com.example.stocktrading.ui.home.Api;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {


    private NewsViewModel galleryViewModel;
    private NewsRecViewAdapter adapter;
    private RecyclerView newsRecView;
    private ArrayList<News> news = new ArrayList<News>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecView = root.findViewById(R.id.newsRecView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecView.setLayoutManager(layoutManager);

        //add arry list


        getNews();

        return root;
    }

    private void getNews() {

        //Rerofit Library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<News> call = api.getNews();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                News newsArr = response.body();

                Log.d("body", newsArr.getItems().getResult().get(0).getTitle());

                for(int i= 15; i< 23; i++) {
                    news.add(new News(newsArr.getItems().getResult().get(i).getTitle().toString(), "By : " + newsArr.getItems().getResult().get(i).getAuthor().toString(),
                            newsArr.getItems().getResult().get(i).getSummary().toString(),
                            newsArr.getItems().getResult().get(i).getMainImage().getResolutions().get(0).getUrl(),
                            newsArr.getItems().getResult().get(i).getLink()));
                }
                adapter = new NewsRecViewAdapter(news, getContext());
                newsRecView.setAdapter(adapter);
                Toast.makeText(getContext(), "Click on card to open news article", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Log.i("error", "error" + t.getLocalizedMessage());
            }
        });
    }

    }
