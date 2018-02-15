package com.example.ibrahim_pc.rv;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.api.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        new FetchPopulaerMovie().execute();
    }

    class FetchPopulaerMovie extends AsyncTask<String, Void, List<Result>> {

        @Override
        protected List<Result> doInBackground(String... params) {

            StringBuilder sb = new StringBuilder();


            try {
                URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=827ccd65a1e2ea6c61def166987b6383&language=en-US&page=1\n");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = "";
                while ((s = br.readLine()) != null) {
                    sb.append(s);

                }

                Log.i("respon", sb.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return parseJsonString(sb.toString());
        }

        public List<Result> parseJsonString(String jsonrspone) {
            List<Result> resultList = new ArrayList<>();
            try {
                JSONObject holeObject = new JSONObject(jsonrspone);
                JSONArray result = holeObject.getJSONArray("results");
                for (int i = 0; i < result.length(); i++) {

                    JSONObject jsonObject = result.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    int vote_count = jsonObject.getInt("vote_count");
                    int id = jsonObject.getInt("id");
                    String posterPath = jsonObject.getString("poster_path");
                    String overView = jsonObject.getString("overview");
                    double voteAverage = jsonObject.getDouble("vote_average");
                    resultList.add(new Result(vote_count, id,voteAverage, title, posterPath, overView));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return resultList;

        }
        List<Result> results;
        @Override
        protected void onPostExecute(List<Result> results) {
            super.onPostExecute(results);
            this.results=results;

            MyRecycleAdapter myRecycleAdapter=new MyRecycleAdapter(getActivity(),results);
            rv.setAdapter(myRecycleAdapter);

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setItem(results);
            Toast.makeText(mainActivity, "onpost", Toast.LENGTH_SHORT).show();

        }


    }


    public interface Callback {
        public void setItem(List<Result> item);
    }


}
