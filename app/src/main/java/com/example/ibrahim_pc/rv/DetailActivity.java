package com.example.ibrahim_pc.rv;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.api.Result;

public class DetailActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent.hasExtra("oneMovie")) {
            Result movie = intent.getExtras().getParcelable("oneMovie");

            if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {

                FragmentDetail fragmentDetail = FragmentDetail.getinstance(movie);

                getSupportFragmentManager().beginTransaction().replace(R.id.container2, fragmentDetail).commit();


            } else {


                FragmentDetail fragmentDetail = FragmentDetail.getinstance(movie);

                getSupportFragmentManager().beginTransaction().replace(R.id.container2, fragmentDetail).commit();


            }


        }


    }
}

