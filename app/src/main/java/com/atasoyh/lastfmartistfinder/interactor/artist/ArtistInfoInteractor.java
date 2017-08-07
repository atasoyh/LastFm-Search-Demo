package com.atasoyh.lastfmartistfinder.interactor.artist;

import com.atasoyh.lastfmartistfinder.model.Artist;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public interface ArtistInfoInteractor {
    Observable<Artist> getInfo(String keyword, String mbid);

}
