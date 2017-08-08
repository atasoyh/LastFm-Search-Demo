
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTrackInfoResponse {

    @SerializedName("track")
    @Expose
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track album) {
        this.track = album;
    }

}
