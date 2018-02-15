package com.example.ibrahim_pc.rv.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ibrahim_pc.rv.api.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrahim_pc on 2018-02-09.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public SqliteHelper(Context context) {
        super(context, Data.db_Name, null, Data.db_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + Data.tableName + " ( " + Data.column_ID + " integer primary key autoincrement, " +
                Data.column_Title + " text, " +
                Data.column_vote + " text, " +
                Data.column_Poster + " integer, " +
                Data.column_Date + " text ," +
                Data.column_OverView + " text )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table " + Data.tableName;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }

    public void AddFaorite(Result movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", movie.getTitle());
        values.put("vote", movie.getVoteAverage());
        values.put("poster", movie.getPosterPath());
        values.put("date", movie.getReleaseDate());
        values.put("overview", movie.getOverview());

        long row = db.insert(Data.tableName, null, values);
        db.close();
    }

    public int deleteFavorite(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] titles={title};
        int i = db.delete(Data.tableName, Data.column_Title + " =? ", titles);
        return i;
    }

    public List<Result> getAllFavorite(){
        List<Result> favoriteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                Data.column_ID,
                Data.column_Title,
                Data.column_vote,
                Data.column_Poster,
                Data.column_Date
        };


        Cursor cursor = db.query(Data.tableName, columns, null, null, null, null, Data.column_Title);

        if (cursor.moveToFirst()) {

            do {
                Result movie = new Result();
                movie.setId(cursor.getInt(cursor.getColumnIndex(Data.column_ID)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(Data.column_vote)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(Data.column_Title)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(Data.column_Poster)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(Data.column_Date)));

                favoriteList.add(movie);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favoriteList;

    }
}
