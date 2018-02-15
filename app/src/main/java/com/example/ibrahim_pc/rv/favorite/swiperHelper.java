package com.example.ibrahim_pc.rv.favorite;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by ibrahim_pc on 2018-02-11.
 */

public class swiperHelper extends ItemTouchHelper.SimpleCallback {

  MyFavoriteAdapter adapter;

  public swiperHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public swiperHelper( MyFavoriteAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN , ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dissmissCard(viewHolder.getAdapterPosition());
    }
}
