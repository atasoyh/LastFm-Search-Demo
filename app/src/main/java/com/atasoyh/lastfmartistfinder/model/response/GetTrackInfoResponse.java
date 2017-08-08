
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTrackInfoResponse {

    @JsonProperty("track")
    private Track track;

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track album) {
        this.track = album;
    }

}
