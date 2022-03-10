package com.example.appmovie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class MovieModel implements Parcelable {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("title")
    private String title;

    public MovieModel(String posterPath, String title) {
        this.posterPath = posterPath;
        this.title = title;
    }
    public MovieModel(){

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

    protected MovieModel(Parcel in) {
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