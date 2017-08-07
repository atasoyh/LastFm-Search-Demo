package com.atasoyh.lastfmartistfinder.interactor.album;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.AlbumMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 07/08/2017.
 */

public class AlbumSearchInteractorImpl implements SearchInteractor {
    LastFmApi api;

    @Inject
    public AlbumSearchInteractorImpl(LastFmApi api) {
        this.api=api;
    }

    @Override
    public Observable<SearchResponse> search(String keyword, int limit, int page) {
        String _limit = String.valueOf(limit);
        String _page = String.valueOf(page);
        if (limit == -1 && page == -1) {
            _limit = null;
            _page = null;
        }
        return this.api.searchAlbum(keyword, _limit, _page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

    }
}
