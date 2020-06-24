package com.example.stocktrading.ui.NewsPage;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocktrading.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRecViewAdapter extends RecyclerView.Adapter<NewsRecViewAdapter.ViewHolder> {

 private ArrayList<News> news;
 private Context context;

    public NewsRecViewAdapter(ArrayList<News> news,Context context) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.title.setText(news.get(position).getTitle());
                holder.summary.setText(news.get(position).getSummary());
                holder.author.setText(news.get(position).getAuther());

        Picasso.with(context)
                .load(news.get(position).getImage())
                .into(holder.image);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(position).getUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView summary;
        private TextView author;
        private CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            summary = itemView.findViewById(R.id.newsSummary);
            author = itemView.findViewById(R.id.author);
            card = itemView.findViewById(R.id.cardNews);
        }
    }
}
