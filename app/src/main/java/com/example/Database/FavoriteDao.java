package com.example.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    public void addData(FavoriteEntity favoriteList);

    @Query("select * from favoritelist")
    public List<FavoriteEntity> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(FavoriteEntity favoriteList);


}