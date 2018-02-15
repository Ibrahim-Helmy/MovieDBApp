package com.example.ibrahim_pc.rv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.DB.SqliteHelper;
import com.example.ibrahim_pc.rv.api.Result;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {
    Context context;
    List<Result> Moves;
    private SqliteHelper sqliteHelper;
    private Result favorite;


    public MyRecycleAdapter(Context context, List<Result> Moves) {
        this.context = context;
        this.Moves = Moves;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(view, this.context, Moves);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Result oneMovie = Moves.get(position);
        holder.titleView.setText(oneMovie.getTitle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w150/" + oneMovie.getPosterPath()).into(holder.posterView);


    }

    @Override
    public int getItemCount() {
        return Moves.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView posterView;
        TextView titleView;
        ImageView shareBtn;

        Context context;
        List<Result> Movies;


        public MyViewHolder(final View itemView, final Context context, final List<Result> Movies) {
            super(itemView);
            this.context = context;
            this.Movies = Movies;


//click event
            itemView.setOnClickListener(this);

            titleView = itemView.findViewById(R.id.title_id);
            posterView = itemView.findViewById(R.id.poster_id);
            shareBtn = itemView.findViewById(R.id.sharebtn_id);

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //  share();

                    Uri uri = Uri.parse("https://image.tmdb.org/t/p/w150/" +Movies.get(getAdapterPosition()).getPosterPath());
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/jpeg");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    context.startActivity(Intent.createChooser(shareIntent, "share poster"));
                }


            });
            //1-favoriteButton

            MaterialFavoriteButton favoriteButton = itemView.findViewById(R.id.favoritBtn_id);
//>>>>
            SharedPreferences  sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);

            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    int position = getAdapterPosition();
                    if (favorite) {
                        SharedPreferences.Editor editor = context.getSharedPreferences("ibrahim", Context.MODE_PRIVATE).edit();
                        editor.putBoolean("favorite added", true);
                        editor.commit();
                        saveFavorite(position);
                        Snackbar.make(buttonView, Movies.get(position).getTitle() + "-->> Added ", Snackbar.LENGTH_LONG).show();

                    } else {
                        String title = Movies.get(position).getTitle();
                        sqliteHelper = new SqliteHelper(context);
                        int i = sqliteHelper.deleteFavorite(title);
                        if (i > 0) {
                            SharedPreferences.Editor editor = context.getSharedPreferences("ibrahim", Context.MODE_PRIVATE).edit();
                            editor.putBoolean("favorite Removed", true);
                            editor.commit();
                            Snackbar.make(buttonView, Movies.get(position).getTitle() + "-->> Removed", Snackbar.LENGTH_LONG).show();
                        }
                    }

                }
            });


        }

        @Override
        public void onClick(View view) {

            //send film main acivity as parameter to setItem Method
            // go to main activity from context

            MainActivity context = (MainActivity) this.context;

            int position = getAdapterPosition();
            Result c = this.Movies.get(position);
            context.setItem(c);

            Toast.makeText(this.context, "on Resycle", Toast.LENGTH_SHORT).show();

            Log.i("onclikkkkkkkkkk", " recycleadapter");

        }

        private void saveFavorite(int position) {
            sqliteHelper = new SqliteHelper(context);
            favorite = new Result();
            Result result = Movies.get(position);

            favorite.setId(result.getId());
            favorite.setTitle(result.getTitle());
            favorite.setVoteAverage(result.getVoteAverage());
            favorite.setReleaseDate(result.getReleaseDate());
            favorite.setPosterPath(result.getPosterPath());
            favorite.setOverview(result.getOverview());

            sqliteHelper.AddFaorite(favorite);
            sqliteHelper.close();
        }

    }



}

