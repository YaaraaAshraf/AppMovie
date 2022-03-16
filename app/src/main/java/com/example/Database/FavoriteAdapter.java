package com.example.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmovie.R;

import java.util.List;

class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteEntity> favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteEntity> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favourite_listitem,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FavoriteEntity fl=favoriteLists.get(i);
        String poster = "https://image.tmdb.org/t/p/w500" + fl.getImage();
        Glide.with(context)
                .load(poster)
                .into(viewHolder.img);
//        if (ListActivity.favoriteDatabase.favoriteDao()
//        Picasso.with(context).load(fl.getImage()).into(viewHolder.img);
        viewHolder.title_fav.setText(fl.getName());
    }
    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title_fav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageview_fav);
            title_fav=(TextView)itemView.findViewById(R.id.title_fav);
        }
    }
}