package com.atasoyh.lastfmartistfinder.interactor.track;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.TrackMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 07/08/2017.
 */

public class TrackSearchInteractorImpl implements SearchInteractor<TrackMatches> {

    LastFmApi api;

    @Inject
    public TrackSearchInteractorImpl(LastFmApi api) {
        this.api = api;
    }

    @Override
    public Observable<SearchResponse<TrackMatches>> search(String keyword, int limit, int page) {
        String _limit = String.valueOf(limit);
        String _page = String.valueOf(page);
        if (limit == -1 && page == -1) {
            _limit = null;
            _page = null;
        }
        return this.api.searchTrack(keyword, _limit, _page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
