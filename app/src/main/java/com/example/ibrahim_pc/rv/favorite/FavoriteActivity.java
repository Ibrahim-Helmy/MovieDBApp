package com.example.ibrahim_pc.rv.favorite;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.DB.SqliteHelper;
import com.example.ibrahim_pc.rv.R;
import com.example.ibrahim_pc.rv.api.Result;

import java.util.List;


public class FavoriteActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        SqliteHelper helper=new SqliteHelper(FavoriteActivity.this);

        List<Result> favoriteList = helper.getAllFavorite();

        if(favoriteList.isEmpty()){
            Toast.makeText(this, "No Favorite Movies", Toast.LENGTH_LONG).show();
        }




        RecyclerView rv=findViewById(R.id.f_rv_id);

        LinearLayoutManager liner=new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(liner);

        MyFavoriteAdapter adapter=new MyFavoriteAdapter(this, favoriteList);
        rv.setAdapter(adapter);


        //3-swipe
        ItemTouchHelper.Callback callback=new swiperHelper(adapter);
        ItemTouchHelper touchHelper=new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rv);





    }


}
