package com.atasoyh.lastfmartistfinder.interactor.artist;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistSearchInteractorImpl implements SearchInteractor<ArtistMatches> {

    LastFmApi api;

    @Inject
    public ArtistSearchInteractorImpl(LastFmApi api) {
        this.api = api;
    }

    @Override
    public Observable<SearchResponse<ArtistMatches>> search(String keyword, int limit, int page) {
        String _limit = String.valueOf(limit);
        String _page = String.valueOf(page);
        if (limit == -1 && page == -1) {
            _limit = null;
            _page = null;
        }
        return this.api.searchArtist(keyword, _limit, _page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io());

    }
}
