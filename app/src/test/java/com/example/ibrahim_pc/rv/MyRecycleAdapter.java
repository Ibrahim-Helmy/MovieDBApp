package com.example.ibrahim_pc.rv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.api.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder>  {
    Context context;
    List<Result> Moves;

    public MyRecycleAdapter(Context context, List<Result> Moves) {
        this.context = context;
        this.Moves = Moves;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);


        return new MyViewHolder(view, context, Moves);
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

        TextView titleView;
        TextView vote;
        ImageView posterView;
        TextView overView;

        Context context;
        List<Result> cites;

        public MyViewHolder(View itemView, Context context, List<Result> cites) {
            super(itemView);
            this.context = context;
            this.cites = cites;
//click event
            itemView.setOnClickListener(this);

            titleView = itemView.findViewById(R.id.title_id);
            posterView = itemView.findViewById(R.id.poster_id);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Result c = this.cites.get(position);

            Intent intent = new Intent(this.context, MainActivity.class);
            intent.putExtra("movie", c);
            this.context.startActivity(intent);
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

        }

    }
}
