package com.example.ibrahim_pc.rv;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahim_pc.rv.DB.SqliteHelper;
import com.example.ibrahim_pc.rv.api.Api;
import com.example.ibrahim_pc.rv.api.Example;
import com.example.ibrahim_pc.rv.api.Result;
import com.example.ibrahim_pc.rv.api.RetrofitClient;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragment extends Fragment {
    RecyclerView rv;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        rv = v.findViewById(R.id.rv);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridLayoutManager grid = new GridLayoutManager(getActivity(), 2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(grid);

        getPopulaerMovies();


    }

    private void getPopulaerMovies() {
        Retrofit retrovitMovie = RetrofitClient.getRetrovitMovie();
        Api api = retrovitMovie.create(Api.class);
  //showPopular
        Call<Example> popular = api.getPopular( "827ccd65a1e2ea6c61def166987b6383");
        popular.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.i("respppppppppppones", response.message());

                MyRecycleAdapter myRecycleAdapter = new MyRecycleAdapter(getActivity(), viewData(response.body()));
                rv.setAdapter(myRecycleAdapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });


    }


    private List<Result> viewData(Example body) {
        List<Result> movies = new ArrayList<>();

        List<Result> results = body.getResults();


        for (int i = 0; i < results.size(); i++) {
            Result oneMovie = results.get(i);
            movies.add(new Result(oneMovie.getId(), oneMovie.getVoteAverage(), oneMovie.getTitle(), oneMovie.getPosterPath(), oneMovie.getReleaseDate(), oneMovie.getOverview()));


        }
        return movies;
    }


    public interface Callback1 {
        public void setItem(Result item);
    }





}
