package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface SearchArtistInteractor {

    Observable<SearchResponse> search(String keyword, int limit, int page);



}
