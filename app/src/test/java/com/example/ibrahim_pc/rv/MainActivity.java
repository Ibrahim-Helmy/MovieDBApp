package com.example.ibrahim_pc.rv;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ibrahim_pc.rv.api.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

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
    public void setItem(List<Result> item) {

        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            //landscap...>>>same mainActivity
        } else {
            //go to detail activity

            Intent i= new Intent(MainActivity.this, DetailActivity.class);
            Bundle bundle=new Bundle();
            bundle.putParcelableArrayList("item", (ArrayList<? extends Parcelable>) item);
            i.putExtras(bundle);
            startActivity(i);

            Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();



        }

    }


}

