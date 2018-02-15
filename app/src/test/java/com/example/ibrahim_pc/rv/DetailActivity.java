package com.example.ibrahim_pc.rv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibrahim_pc.rv.api.Result;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
ImageView imageView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent.hasExtra("item")){
            ArrayList<Result> item = intent.getExtras().getParcelableArrayList("item");
            FragmentDetail fragmentDetail = FragmentDetail.getinstance(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.container2,fragmentDetail).commit();
        }

    }
}
