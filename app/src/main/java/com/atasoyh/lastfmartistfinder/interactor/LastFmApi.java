package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface LastFmApi {
    String ENDPOINT = "http://ws.audioscrobbler.com/2.0/";

    /**
     * @param keyword The artist name
     * @param limit   Optional. The number of results to fetch per page. Defaults to 30.
     * @param page    The page number to fetch. Defaults to first page.
     * @return {@link Results}
     */
    @GET("?method=artist.search")
    public Observable<SearchResponse> search(@Query("artist") String keyword, @Query("limit") String limit, @Query("page") String page);

    /**
     * @param artist The artist name
     * @param mbid   (Optional) : The musicbrainz id for the artist
     * @return
     */
    @GET("?method=artist.getinfo")
    public Observable<GetArtistInfoResponse> getArtistInfo(@Query("artist") String artist, @Query("mbid") String mbid);


}
