package com.example.ibrahim_pc.rv;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.favorite.FavoriteActivity;
import com.example.ibrahim_pc.rv.api.Result;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback1{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {

            getSupportFragmentManager().beginTransaction().replace(R.id.left, new MainFragment()).commit();


        } else {

            getSupportFragmentManager().beginTransaction().replace(R.id.mainPortrit_id, new MainFragment()).commit();

        }
    }

    @Override
    public void setItem(Result item) {


        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {

            getSupportFragmentManager().beginTransaction().replace(R.id.right, FragmentDetail.getinstance(item)).commit();
        } else {

            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("oneMovie", item);
            i.putExtra("id",item.getId());

            startActivity(i);



        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.favorite_id :
                Intent intent=new Intent(MainActivity.this,FavoriteActivity.class);

                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}

