
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetArtistInfoResponse {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
