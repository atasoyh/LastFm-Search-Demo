package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoInteractorImpl implements ArtistInfoInteractor {

    LastFmApi api;

    @Inject
    public ArtistInfoInteractorImpl(LastFmApi api) {
        this.api = api;
    }

    @Override
    public Observable<GetArtistInfoResponse> search(String keyword, String mbid) {
        return api.getArtistInfo(keyword,mbid);
    }
}
