package com.atasoyh.lastfmartistfinder.interactor.artist;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    public Observable<Artist> getInfo(String keyword, String mbid) {
        return api.getArtistInfo(keyword,mbid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).map(new Function<GetArtistInfoResponse, Artist>() {
                    @Override
                    public Artist apply(GetArtistInfoResponse getArtistInfoResponse) throws Exception {
                        return getArtistInfoResponse.getArtist();
                    }
                });
    }
}
