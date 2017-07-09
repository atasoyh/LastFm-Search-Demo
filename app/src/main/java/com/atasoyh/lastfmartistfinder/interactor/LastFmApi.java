package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.Results;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface LastFmApi {
    String ENDPOINT = "http://ws.audioscrobbler.com/2.0";

    /**
     *
     * @param keyword The artist name
     * @param limit Optional. The number of results to fetch per page. Defaults to 30.
     * @param page The page number to fetch. Defaults to first page.
     * @return {@link Results}
     */
    @GET("/?method=artist.search&format=json")
    Call<Results> search(@Query("artist") String keyword, @Query("limit") String limit, @Query("page") String page );


}
