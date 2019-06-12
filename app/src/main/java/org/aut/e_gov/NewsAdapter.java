package org.aut.e_gov;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.e_gov.models.DatabaseAccess;
import org.aut.e_gov.models.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context mCtx;
    private List<News> newsList;
    private int cartId;
    private String userId;
    public int totalPrice;
    private int i;
    public NewsAdapter(Context mCtx, List<News> newsList, String userId) {
        this.mCtx = mCtx;
        this.newsList = newsList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        this.i = i;
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News news = newsList.get(i);
        newsViewHolder.newsId = news.getNewsId();
        newsViewHolder.textViewTitle.setText(news.getTitle());
        newsViewHolder.textViewRating.setText(news.getRating() + "");
        newsViewHolder.textViewDesc.setText(news.getShortDesc());
        newsViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(news.getImage()));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        int newsId;
        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating;
        Button btnRating;
        EditText edtCount;/////////////////////////////////////////////////////////////
        final DatabaseAccess dbAccess = new DatabaseAccess(mCtx);

        public NewsViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            btnRating = itemView.findViewById(R.id.btnRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        News clickedDataItem = newsList.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getNewsId(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



            btnRating.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx , FeedbackActivity.class);
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        News clickedDataItem = newsList.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getNewsId(), Toast.LENGTH_SHORT).show();
                    }
                    intent.putExtra("newsId", newsList.get(pos).getNewsId());
                    mCtx.startActivity(intent);
                }
            });

        }

    }
}
