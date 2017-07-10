package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public interface ArtistInfoInteractor {
    Observable<GetArtistInfoResponse> search(String keyword, String mbid);

}
