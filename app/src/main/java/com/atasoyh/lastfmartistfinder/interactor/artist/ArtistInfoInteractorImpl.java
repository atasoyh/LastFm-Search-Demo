package com.atasoyh.lastfmartistfinder.interactor.artist;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.Artist;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoInteractorImpl implements ArtistInfoInteractor {

    private final String artistName;
    private final String mbid;
    LastFmApi api;

    @Inject
    public ArtistInfoInteractorImpl(LastFmApi api,String artistName,  String mbid) {
        this.artistName = artistName;
        this.mbid = mbid;
        this.api = api;
    }

    @Override
    public Observable<Artist> getInfo() {
        return api.getArtistInfo(artistName, mbid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).map(getArtistInfoResponse -> getArtistInfoResponse.getArtist());
    }
}
