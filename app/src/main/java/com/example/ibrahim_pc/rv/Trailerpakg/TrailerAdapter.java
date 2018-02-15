
package com.example.ibrahim_pc.rv.Trailerpakg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.R;

import java.util.List;

/**
 * Created by ibrahim_pc on 2018-02-13.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {
    Context context;
    List<Trailer> trailerList;

    public TrailerAdapter(Context context, List<Trailer> trailerList) {
        this.context = context;
        this.trailerList = trailerList;
    }

    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.single_trailer, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(trailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_trailer_id);
            iv = itemView.findViewById(R.id.image_trailer_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {

                        Trailer clickedtrailer = trailerList.get(pos);
                        String videiId= clickedtrailer.getKey();

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videiId));
                        intent.putExtra("video_id", videiId);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(context, "you clicked" + clickedtrailer.getName(), Toast.LENGTH_SHORT).show();


                    }
                }
            });
        }
    }

}
