package com.example.appmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Database.FavoriteEntity;

import java.util.ArrayList;
import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Movie> movieList;
    private List<Movie> movieListFiltered;

    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
        movieListFiltered = movieList;
    }
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(movieList.get(i).getTitle());
        String poster = "https://image.tmdb.org/t/p/w500" + movieList.get(i).getPosterPath();
        Glide.with(mContext)
                .load(poster)
                .into(viewHolder.img);

        if (ListActivity.favoriteDatabase.favoriteDao().isFavorite(movieList.get(i).getId()) == 1)
            viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite);
        else
            viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite_border);

        viewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoriteEntity favoriteEntity = new FavoriteEntity();
                int id = movieList.get(i).getId();
                String image = movieList.get(i).getPosterPath();
                String name = movieList.get(i).getTitle();
                favoriteEntity.setId(id);
                favoriteEntity.setImage(image);
                favoriteEntity.setName(name);

                if (ListActivity.favoriteDatabase.favoriteDao().isFavorite(id) != 1) {
                    viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite);
                    ListActivity.favoriteDatabase.favoriteDao().addData(favoriteEntity);
                    Toast.makeText(mContext,"Add to Favourite",Toast.LENGTH_LONG).show();
                } else {
                    viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite_border);
                    ListActivity.favoriteDatabase.favoriteDao().delete(favoriteEntity);
                    Toast.makeText(mContext,"Delete From Favourite",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return movieListFiltered.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                   movieListFiltered = movieList;
                } else {
                    List<Movie> filteredList = new ArrayList<>();
                    for (Movie row : movieList) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    movieListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        ImageView fav_btn;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.imageview);
            fav_btn=(ImageView)view.findViewById(R.id.img_favorite);

        }
    }
}
