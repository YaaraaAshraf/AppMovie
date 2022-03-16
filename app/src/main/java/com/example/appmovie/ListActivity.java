package com.example.appmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.Database.FavoriteDatabase;
import com.example.Database.FavouriteList;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private SearchView searchView;
    MovieAdapter firstAdapter;
    public static FavoriteDatabase favoriteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        favoriteDatabase= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        load();
    }
    private void load() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<Movie> movies = response.body().getResults();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            firstAdapter = new MovieAdapter(getApplicationContext(), movies);
                            RecyclerView firstRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                            LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            firstRecyclerView.setLayoutManager(firstManager);
                            firstRecyclerView.setAdapter(firstAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    firstAdapter.getFilter().filter(query);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String query) {
                    firstAdapter.getFilter().filter(query);
                    return false;
                }
            });
            return true;
        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_upcoming:
                Intent intent=new Intent(ListActivity.this,UpcomingList.class);
                startActivity(intent);
//                Toast.makeText(this,"click1",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_rated:
                Intent intentrated=new Intent(ListActivity.this, TopRatedList.class);
                startActivity(intentrated);
                return true;
            case R.id.favourites:
                Intent intentfav=new Intent(ListActivity.this, FavouriteList.class);
                startActivity(intentfav);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}