package com.example.appmovie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Movie implements Parcelable {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;
    private int id;
    public Movie(String posterPath, String title,int id) {
        this.posterPath = posterPath;
        this.title = title;
        this.id=id;
    }
    public Movie(){

    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }




    protected Movie(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(posterPath);
        parcel.writeString(title);
    }
}
