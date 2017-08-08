
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetArtistInfoResponse {

    @JsonProperty("artist")
    private Artist artist;
    @JsonProperty("artist")
    public Artist getArtist() {
        return artist;
    }
    @JsonProperty("artist")
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
