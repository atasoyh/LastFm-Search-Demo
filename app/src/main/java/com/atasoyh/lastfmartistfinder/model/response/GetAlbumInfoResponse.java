
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAlbumInfoResponse {

    @JsonProperty("album")
    private Album album;
    @JsonProperty("album")
    public Album getAlbum() {
        return album;
    }
    @JsonProperty("album")
    public void setAlbum(Album album) {
        this.album = album;
    }

}
