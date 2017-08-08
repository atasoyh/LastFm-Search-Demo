package com.atasoyh.lastfmartistfinder.interactor.album;

import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public interface AlbumInfoInteractor {
    Observable<Album> getInfo();

}
