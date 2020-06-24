package com.example.stocktrading.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocktrading.DataBaseHelper;
import com.example.stocktrading.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    //db instance
    public static DataBaseHelper myDb;

    // Declare Variables
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private String[] stockSymbols;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    private RelativeLayout card;
    public TextView stockNameInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        myDb = new DataBaseHelper(getContext());
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "Add new stock by click on add button", Toast.LENGTH_SHORT).show();

        }

        while (res.moveToNext()) {
            stocks.add(new Stock(res.getString(1), res.getString(2), res.getString(18), res.getFloat(3),
                    res.getFloat(4), res.getFloat(5),
                    res.getString(6), res.getString(7), res.getString(8), res.getInt(9),
                    res.getString(10), res.getString(11), res.getString(12), res.getString(13),
                    res.getString(14), res.getString(15), res.getString(16),
                    res.getString(17)));
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID :" + res.getString(0));
            buffer.append("NAME :" + res.getString(1));
            buffer.append("EXCHANGE :" + res.getString(2));
            buffer.append("CLOSE :" + res.getString(3));
            buffer.append("OPEN :" + res.getString(4));
            buffer.append("PRICE :" + res.getString(5));
        }

        //init RecyclerView -> (show stocks added by user in recyclerview)
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.StockRecView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        StockRecViewAdapter adapter = new StockRecViewAdapter(stocks, getContext());
        recyclerView.setAdapter(adapter);

        // Generate stock data in search box (stock symbol string array in resource file)
        stockSymbols = getResources().getStringArray(R.array.symbol);
        ArrayAdapter<String> My_arr_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, stockSymbols);
        AutoCompleteTextView completeTextView = (AutoCompleteTextView) root.findViewById(R.id.autotv);
        completeTextView.setThreshold(2);
        completeTextView.setAdapter(My_arr_adapter);
        final String[] stock = new String[1];
        //OnItemClick Add stock with same name to stock list
        RelativeLayout search = root.findViewById(R.id.searchBar);

        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stock[0] = parent.getItemAtPosition(position).toString();
                recyclerView.setVisibility(View.VISIBLE);
                search.setVisibility(View.INVISIBLE);
                getStockSearch(stock[0]);
                Toast toast = Toast.makeText(getContext(), stock[0] + " added", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                HomeFragment rSum = new HomeFragment();
                getFragmentManager().beginTransaction().detach(rSum).attach(rSum).commit();
            }
        });


        //open search box to add stock when clicked on FloatingActionButton
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.INVISIBLE);
                search.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }


    //Fetch Stock data using API interface
    private void getStockSearch(String stock) {

        //Rerofit Library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Stock> call = api.getStock(stock);

        call.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, Response<Stock> response) {

                Stock stock = response.body();

                Log.d("shortName", stock.getQuoteType().getShortName());
                Log.d("exchange", stock.getQuoteType().getExchange());
                Log.d("close", stock.getSummaryDetail().getPreviousClose().getRaw().toString());

//                stocks.add(new Stock(stock.getQuoteType().getShortName(),
//                        stock.getQuoteType().getExchange(),
//                        stock.getQuoteType().getSymbol(),
//                        stock.getSummaryDetail().getPreviousClose().getRaw(),
//                        stock.getSummaryDetail().getRegularMarketOpen().getRaw(),
//                        stock.getPrice().getRegularMarketPrice().getRaw(),
//                        stock.getSummaryDetail().getBid().getFmt(),
//                        stock.getSummaryDetail().getAsk().getFmt(),
//                        stock.getAssetProfile().getSector(),
//                        stock.getAssetProfile().getFullTimeEmployees(),
//                        stock.getAssetProfile().getLongBusinessSummary(),
//                        stock.getAssetProfile().getCity(),
//                        stock.getAssetProfile().getCountry(),
//                        stock.getAssetProfile().getWebsite(),
//                        stock.getSummaryDetail().getAverageDailyVolume10Day().getFmt(),
//                        stock.getPrice().getMarketCap().getFmt(),
//                        stock.getPrice().getRegularMarketChange().getFmt(),
//                        stock.getPrice().getRegularMarketVolume().getFmt()));

                boolean isInserted = myDb.insertData(stock.getQuoteType().getShortName(), stock.getQuoteType().getExchange(),
                        stock.getSummaryDetail().getPreviousClose().getRaw(),
                        stock.getSummaryDetail().getRegularMarketOpen().getRaw(),
                        stock.getPrice().getRegularMarketPrice().getRaw(),
                        stock.getSummaryDetail().getBid().getFmt(),
                        stock.getSummaryDetail().getAsk().getFmt(),
                        stock.getAssetProfile().getSector(),
                        stock.getAssetProfile().getFullTimeEmployees(),
                        stock.getAssetProfile().getLongBusinessSummary(),
                        stock.getAssetProfile().getCity(),
                        stock.getAssetProfile().getCountry(),
                        stock.getAssetProfile().getWebsite(),
                        stock.getSummaryDetail().getAverageDailyVolume10Day().getFmt(),
                        stock.getPrice().getMarketCap().getFmt(),
                        stock.getPrice().getRegularMarketChange().getFmt(),
                        stock.getPrice().getRegularMarketVolume().getFmt(),
                        stock.getQuoteType().getSymbol());

                if (isInserted = true) {
                    Toast.makeText(getContext(), "inserted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "not inserted", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {

                Log.i("error", "error" + t.getLocalizedMessage());
            }
        });


    }

    public void AddData() {


    }
}
