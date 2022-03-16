package com.example.appmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.MyViewHolder> {
    private Context mContext;
    private List<MovieModel> movieList;
    public TopRatedAdapter(Context mContext, List<MovieModel> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }
    @Override
    public TopRatedAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.top_rateditem, viewGroup, false);
        return new TopRatedAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final TopRatedAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(movieList.get(i).getTitle());
        String poster = "https://image.tmdb.org/t/p/w500" + movieList.get(i).getPosterPath();
        Glide.with(mContext)
                .load(poster)
                .into(viewHolder.img);
    }
    @Override
    public int getItemCount() {
        return 5;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.top_rated_movie);
            img = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}

