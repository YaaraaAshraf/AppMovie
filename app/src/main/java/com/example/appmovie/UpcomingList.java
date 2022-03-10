package com.example.appmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingList extends AppCompatActivity {
    UpcomingMovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_list);
        loadMovies();
    }
    private void loadMovies() {
        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<UpcomingResponse> call = apiService.getUpcomingMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<UpcomingResponse>() {
                @Override
                public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                    List<MovieModel> movies = response.body().getResults();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            movieAdapter = new UpcomingMovieAdapter(getApplicationContext(), movies);
                            RecyclerView RecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                            RecyclerView.setLayoutManager(firstManager);
                            RecyclerView.setAdapter(movieAdapter);
                        }
                    }
                }
                @Override
                public void onFailure(Call<UpcomingResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}