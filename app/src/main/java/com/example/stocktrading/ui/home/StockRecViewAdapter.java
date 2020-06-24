package com.example.stocktrading.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocktrading.DataBaseHelper;
import com.example.stocktrading.R;

import java.security.AccessController;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static com.example.stocktrading.ui.home.HomeFragment.myDb;

public class StockRecViewAdapter extends RecyclerView.Adapter<StockRecViewAdapter.ViewHolder> {

    Context context;

    private ArrayList<Stock> stock;


    public StockRecViewAdapter(ArrayList<Stock> data, Context context) {

        this.stock = data;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stockName.setText(stock.get(position).getShortName());
        holder.stockDec.setText(stock.get(position).getExchange());
        holder.close.setText(stock.get(position).getPreviousClose().toString());
        holder.open.setText(stock.get(position).getOpen().toString());
        holder.price.setText(stock.get(position).getPrice1().toString());


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deleteStock = myDb.deleteData(stock.get(position).getSymbol());
                notifyDataSetChanged();
                if (deleteStock > 0) {
                    Log.d("deleted", "deleted");
                    Toast.makeText(context, stock.get(position).getShortName() + " Deleted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MoreInfo.class);
                intent.putExtra("symbol", stock.get(position).getSymbol());
                intent.putExtra("name", stock.get(position).getShortName());
                intent.putExtra("bid", stock.get(position).getBid());
                intent.putExtra("close", stock.get(position).getPreviousClose().toString());
                intent.putExtra("open", stock.get(position).getOpen().toString());
                intent.putExtra("price", stock.get(position).getPrice1());
                intent.putExtra("ask", stock.get(position).getAsk());
                intent.putExtra("sector", stock.get(position).getSector());
                intent.putExtra("employee", stock.get(position).getFullTimeEmployees().toString());
                intent.putExtra("summary", stock.get(position).getLongBusinessSummary());
                intent.putExtra("city", stock.get(position).getCity());
                intent.putExtra("country", stock.get(position).getCountry());
                intent.putExtra("website", stock.get(position).getWebsite());
                intent.putExtra("dailyVolume", stock.get(position).getAverageDailyVolume10Day());
                intent.putExtra("cap", stock.get(position).getMarketCap());
                intent.putExtra("change", stock.get(position).getRegularMarketChange());
                intent.putExtra("volume", stock.get(position).getRegularMarketVolume());
                Log.d("symbol", stock.get(position).getSymbol());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stock.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView stockName;
        private TextView stockDec;
        private ImageView remove;
        private ImageView add;
        private CardView parent;
        private TextView close;
        private TextView open;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            stockName = itemView.findViewById(R.id.stockName);
            stockDec = itemView.findViewById(R.id.desc);
            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);
            parent = itemView.findViewById(R.id.parent);
            close = itemView.findViewById(R.id.close);
            open = itemView.findViewById(R.id.open);
            price = itemView.findViewById(R.id.price);


        }
    }
}
