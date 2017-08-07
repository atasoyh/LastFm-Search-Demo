
package com.atasoyh.lastfmartistfinder.model.response;

import com.atasoyh.lastfmartistfinder.model.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse<T> {

    @SerializedName("results")
    @Expose
    private Results<T> results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
