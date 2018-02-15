package com.example.ibrahim_pc.rv;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibrahim_pc.rv.api.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetail extends Fragment {


    public FragmentDetail() {
        // Required empty public constructor
    }
    TextView title,rate,overView;
   ImageView iv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_detail, container, false);
          title=v.findViewById(R.id.detailTitle);
        iv=v.findViewById(R.id.detail_image_id);
    return v;
    }

    public static FragmentDetail getinstance(List<Result> item) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("item", (ArrayList<? extends Parcelable>) item);
        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(bundle);
        return fragmentDetail;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        ArrayList<Result> item = bundle.getParcelableArrayList("item");

        for (int i=0;i<item.size();i++) {

            title.setText(item.get(i).getTitle());
            Picasso.with(getContext()).load("https://image.tmdb.org/t/p/w150/" + item.get(i).getPosterPath()).into(iv);
        }
    }
}
