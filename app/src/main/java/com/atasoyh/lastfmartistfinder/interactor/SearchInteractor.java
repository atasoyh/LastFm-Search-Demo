package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 07/08/2017.
 */

public interface SearchInteractor<T> {
    Observable<SearchResponse<T>> search(String keyword, int limit, int page);

}
