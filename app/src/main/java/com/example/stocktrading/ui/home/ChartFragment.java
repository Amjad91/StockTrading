package com.example.stocktrading.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stocktrading.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ChartFragment extends Fragment {

    private RelativeLayout r;
    LineChart chart;
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> close = new ArrayList<Integer>();
   TextView title;
    public ApiInterface apiInterface;


    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String symbol = ((MoreInfo) getActivity()).symbol;
       Log.d("from_MoreInfo", symbol);

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        chart = (LineChart) view.findViewById(R.id.linechart1);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        /**
         *  api call to get some data from server
         *
         */
        getData(symbol);

        // Inflate the layout for this fragment
        return view;
    }

    private void getData(String s) {
        Call<Chart> piChartCall = apiInterface.init(s);
        piChartCall.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, retrofit2.Response<Chart> response) {
                Log.d("CHART_RESPONSE", "" + response.body().getDataset().getColumnNames());
                setChartData(response.body());

            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {

            }
        });

    }

    public void setChartData(Chart body) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();
        ArrayList<Entry> entries3 = new ArrayList<>();


        for (int i = 0; i < body.getDataset().getData().get(0).size(); i++) {
            String day = body.getDataset().getData().get(i).get(0);
            date.add(day);
            String closey = body.getDataset().getData().get(i).get(4);
            double d = Double.parseDouble(closey);
            int closee = (int) d;
            entries.add(new Entry(i, closee));


            String high = body.getDataset().getData().get(i).get(2);
            double d2 = Double.parseDouble(high);
            int highh = (int) d2;
            entries2.add(new Entry(i, highh));

            String low = body.getDataset().getData().get(i).get(3);
            double d3 = Double.parseDouble(low);
            int loww = (int) d3;
            entries3.add(new Entry(i, loww));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Close ");
        LineDataSet dataSet2 = new LineDataSet(entries2, "High ");
        LineDataSet dataSet3 = new LineDataSet(entries3, "Low ");

        dataSet.setColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        dataSet2.setColor(ContextCompat.getColor(getActivity(), R.color.red));
        dataSet2.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.red));

        dataSet3.setColor(ContextCompat.getColor(getActivity(), R.color.yellow));
        dataSet3.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(dataSet);
        dataSets.add(dataSet2);
        dataSets.add(dataSet3);
        //****
        // Controlling X axis
        XAxis xAxis = chart.getXAxis();
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Customizing x axis value
        //
        Log.d("DATE_Array", "" + date);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return date.get((int) value);
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        //***
        // Controlling right side of y axis
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        // Setting Data
        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.animateX(2500);

        //refresh
        chart.invalidate();

    }
}







