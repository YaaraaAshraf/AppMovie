package com.example.Database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appmovie.ListActivity;
import com.example.appmovie.R;

import java.util.List;

public class FavouriteList extends AppCompatActivity {
    private FavoriteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        List<FavoriteEntity> favorites = ListActivity.favoriteDatabase.favoriteDao().getFavoriteData();

        RecyclerView rv = findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FavoriteAdapter(favorites,getApplicationContext());
        rv.setAdapter(adapter);

    }
}