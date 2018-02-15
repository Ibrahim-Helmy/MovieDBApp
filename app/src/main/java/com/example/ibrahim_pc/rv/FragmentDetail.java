package com.example.ibrahim_pc.rv;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.DB.SqliteHelper;
import com.example.ibrahim_pc.rv.Trailerpakg.Trailer;
import com.example.ibrahim_pc.rv.Trailerpakg.TrailerAdapter;
import com.example.ibrahim_pc.rv.Trailerpakg.TrailerResponse;
import com.example.ibrahim_pc.rv.api.Api;
import com.example.ibrahim_pc.rv.api.Result;
import com.example.ibrahim_pc.rv.api.RetrofitClient;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetail extends Fragment {

    private static Bundle bundle;
    private RecyclerView recyclerView;
    private TrailerAdapter adapter;
    private List<Trailer> trailerlist;
    private String API_KEY = "827ccd65a1e2ea6c61def166987b6383";
    private int MovieId;

    public FragmentDetail() {
        // Required empty public constructor
    }

    TextView title, rate, overView, date;
    ImageView parImag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_detail, container, false);

        recyclerView = v.findViewById(R.id.rv_trailer_id);


        title = v.findViewById(R.id.detailTitle);
        rate = v.findViewById(R.id.detailRate_id);
        overView = v.findViewById(R.id.detailOverview_id);
        date = v.findViewById(R.id.date_id);
        parImag = v.findViewById(R.id.app_bar_image);


        initiView();

        return v;
    }

    public static FragmentDetail getinstance(Result item) {
        bundle = new Bundle();
        bundle.putParcelable("m", item);
        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        return fragmentDetail;
    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
       Result m = bundle.getParcelable("m");

        title.setText(m.getTitle());
        Picasso.with(getContext()).load("https://image.tmdb.org/t/p/w150/" + m.getPosterPath()).into(parImag);

        rate.setText("Vote :  " + m.getVoteAverage() + "");
        date.setText(m.getReleaseDate());
        overView.setText(m.getOverview());


    }

    private void initiView() {

        trailerlist = new ArrayList<>();

        adapter = new TrailerAdapter(getActivity(), trailerlist);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        Loadjson();
    }

    private void Loadjson() {
            Result m = bundle.getParcelable("m");
            MovieId = m.getId();

        try {

            if (API_KEY.isEmpty()) {

                return;
            }

            RetrofitClient client = new RetrofitClient();
            Api api = client.getRetrovitMovie().create(Api.class);


            Call<TrailerResponse> trailer = api.getTrailer(MovieId, API_KEY);
            trailer.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {

                    List<Trailer> trailers = response.body().getResults();



                    recyclerView.setAdapter(new TrailerAdapter(getActivity(), trailers));
                    recyclerView.smoothScrollToPosition(0);


                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "error fatching", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage() + " hereeeeeee", Toast.LENGTH_SHORT).show();
        }

    }



}
