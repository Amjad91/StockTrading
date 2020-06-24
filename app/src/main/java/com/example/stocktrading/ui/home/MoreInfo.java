package com.example.stocktrading.ui.home;


import android.content.Intent;
import android.os.Bundle;

import com.example.stocktrading.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;


public class MoreInfo extends AppCompatActivity {
    public String symbol;
    public String bid;
    private TextView title;
    public String stockName;
    public String close;
    public String open;
    public String price;
    public String ask;
    public String sector;
    public String employee;
    public String summary;
    public String city;
    public String country;
    public String website;
    public String dVolume;
    public String cap;
    public String change;
    public String volume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Intent i = this.getIntent();
        Bundle b = i.getExtras();
        symbol = b.getString("symbol");
        bid = b.getString("bid");
        stockName = b.getString("name");
        close = b.getString("close");
        open = b.getString("open");
        price = b.getString("price");
        ask = b.getString("ask");
        sector = b.getString("sector");
        employee = b.getString("employee");
        summary = b.getString("summary");
        city = b.getString("city");
        country = b.getString("country");
        website = b.getString("website");
        dVolume = b.getString("dailyVolume");
        cap = b.getString("cap");
        change = b.getString("change");
        volume = b.getString("volume");

        Log.d("symbol", bid);

        title = (TextView) findViewById(R.id.title);
        title.setText(stockName + " Stock Data");

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);


        tabs.addTab(tabs.newTab().setText("Chart"));
        tabs.addTab(tabs.newTab().setText("More info"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}