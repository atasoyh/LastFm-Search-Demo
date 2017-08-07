
package com.atasoyh.lastfmartistfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results<T> {

    @SerializedName("opensearch:Query")
    @Expose
    private OpensearchQuery opensearchQuery;
    @SerializedName("opensearch:totalResults")
    @Expose
    private String opensearchTotalResults;
    @SerializedName("opensearch:startIndex")
    @Expose
    private String opensearchStartIndex;
    @SerializedName("opensearch:itemsPerPage")
    @Expose
    private String opensearchItemsPerPage;
    @SerializedName("artistmatches")
    @Expose
    private T matches;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public OpensearchQuery getOpensearchQuery() {
        return opensearchQuery;
    }

    public void setOpensearchQuery(OpensearchQuery opensearchQuery) {
        this.opensearchQuery = opensearchQuery;
    }

    public String getOpensearchTotalResults() {
        return opensearchTotalResults;
    }

    public void setOpensearchTotalResults(String opensearchTotalResults) {
        this.opensearchTotalResults = opensearchTotalResults;
    }

    public String getOpensearchStartIndex() {
        return opensearchStartIndex;
    }

    public void setOpensearchStartIndex(String opensearchStartIndex) {
        this.opensearchStartIndex = opensearchStartIndex;
    }

    public String getOpensearchItemsPerPage() {
        return opensearchItemsPerPage;
    }

    public void setOpensearchItemsPerPage(String opensearchItemsPerPage) {
        this.opensearchItemsPerPage = opensearchItemsPerPage;
    }

    public T getMatches() {
        return matches;
    }

    public void setMatches(T matches) {
        this.matches = matches;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}
