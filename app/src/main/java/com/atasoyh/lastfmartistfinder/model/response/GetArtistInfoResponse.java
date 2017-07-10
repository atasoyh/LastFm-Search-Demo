
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetArtistInfoResponse {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
