package com.example.stocktrading.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stocktrading.R;

public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);


        String bid1 = ((MoreInfo) getActivity()).bid;
        String ask = ((MoreInfo) getActivity()).ask;
        String stockName = ((MoreInfo) getActivity()).stockName;
        String close = ((MoreInfo) getActivity()).close;
        String open = ((MoreInfo) getActivity()).open;
        String price = ((MoreInfo) getActivity()).price;
        String sector = ((MoreInfo) getActivity()).sector;
        String employee = ((MoreInfo) getActivity()).employee;
        String summary = ((MoreInfo) getActivity()).summary;
        String city = ((MoreInfo) getActivity()).city;
        String country = ((MoreInfo) getActivity()).country;
        String website = ((MoreInfo) getActivity()).website;
        String dVolume = ((MoreInfo) getActivity()).dVolume;
        String cap = ((MoreInfo) getActivity()).cap;
        String change = ((MoreInfo) getActivity()).change;
        String volume = ((MoreInfo) getActivity()).volume;


        TextView name = view.findViewById(R.id.stockNameInfo);
        name.setText(stockName);


        TextView closeInfo = view.findViewById(R.id.preClose);
        closeInfo.setText(close);

        TextView openInfo = view.findViewById(R.id.openInfo);
        openInfo.setText(open);

        TextView bidInfo = view.findViewById(R.id.bid);
        bidInfo.setText(bid1);

        TextView askInfo = view.findViewById(R.id.ask);
        askInfo.setText(ask);

        TextView volumeInfo = view.findViewById(R.id.volume);
        volumeInfo.setText(dVolume);


        TextView capInfo = view.findViewById(R.id.cap);
        capInfo.setText(cap);

        TextView changeInfo = view.findViewById(R.id.change);
        changeInfo.setText(change);


        TextView sectorInfo = view.findViewById(R.id.sector);
        sectorInfo.setText(sector);

        TextView employeeInfo = view.findViewById(R.id.employee);
        employeeInfo.setText(employee);

        TextView cityInfo = view.findViewById(R.id.city);
        cityInfo.setText(city);

        TextView countryInfo = view.findViewById(R.id.country);
        countryInfo.setText(country);

        TextView summaryInfo = view.findViewById(R.id.summary);
        summaryInfo.setText(summary);

        TextView websiteInfo = view.findViewById(R.id.website);
        websiteInfo.setText(website);


        // Inflate the layout for this fragment
        return view;
    }
}
