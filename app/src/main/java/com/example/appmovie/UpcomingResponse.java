package com.example.appmovie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
class UpcomingResponse implements Parcelable {
    @SerializedName("results")
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    protected UpcomingResponse(Parcel in) {
    }

    public static final Creator<UpcomingResponse> CREATOR = new Creator<UpcomingResponse>() {
        @Override
        public UpcomingResponse createFromParcel(Parcel in) {
            return new UpcomingResponse(in);
        }

        @Override
        public UpcomingResponse[] newArray(int size) {
            return new UpcomingResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}