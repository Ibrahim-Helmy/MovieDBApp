package com.example.ibrahim_pc.rv.favorite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.DB.Data;
import com.example.ibrahim_pc.rv.DB.SqliteHelper;
import com.example.ibrahim_pc.rv.R;
import com.example.ibrahim_pc.rv.api.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.MyViewHolder> {
    Context context;
    List<Result> favoriteList;
    private SQLiteDatabase db;
    private SqliteHelper helper;

    public MyFavoriteAdapter(Context context, List<Result> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_favorite, parent, false);
        
         helper=new SqliteHelper(context);
         db = helper.getWritableDatabase();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result movie = favoriteList.get(position);









        holder.title.setText(movie.getTitle().toString());
        holder.rate.setText(movie.getVoteAverage() + "");
        holder.date.setText(movie.getReleaseDate().toString());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w150/" + movie.getPosterPath()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, rate, date;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.fav_image_id);
            title = itemView.findViewById(R.id.fav_Title_id);
            rate = itemView.findViewById(R.id.fav_Rate_id);
            date = itemView.findViewById(R.id.fav_date_id);
        }
    }
//1-swipe
    public void dissmissCard(int pos) {
        String title = favoriteList.get(pos).getTitle();
        favoriteList.remove(pos);
        helper.deleteFavorite(title);
        this.notifyItemRemoved(pos);
        Toast.makeText(context, title+" Deleted", Toast.LENGTH_SHORT).show();

        if (favoriteList.isEmpty()){
            Toast.makeText(context, "Delete All Favorite Movies", Toast.LENGTH_SHORT).show();
        }

    }
}
