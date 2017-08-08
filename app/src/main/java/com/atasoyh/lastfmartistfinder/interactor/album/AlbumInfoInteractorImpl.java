package com.atasoyh.lastfmartistfinder.interactor.album;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.response.GetAlbumInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class AlbumInfoInteractorImpl implements AlbumInfoInteractor {

    private final String albumName;
    private final String artistName;
    LastFmApi api;

    @Inject
    public AlbumInfoInteractorImpl(LastFmApi api, String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.api = api;
    }

    @Override
    public Observable<Album> getInfo() {
        return api.getAlbumInfo(albumName, artistName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map((Function<GetAlbumInfoResponse, Album>) getAlbumInfoResponse -> getAlbumInfoResponse.getAlbum());
    }
}
