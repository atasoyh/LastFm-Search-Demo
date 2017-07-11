package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class SearchArtistInteractorImpl implements SearchArtistInteractor {

    LastFmApi api;

    @Inject
    public SearchArtistInteractorImpl(LastFmApi api) {
        this.api = api;
    }

    @Override
    public Observable<SearchResponse> search(String keyword, int limit, int page) {
        String _limit = String.valueOf(limit);
        String _page = String.valueOf(page);
        if (limit == -1 && page == -1) {
            _limit = null;
            _page = null;
        }
        Observable.just(new SearchResponse()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return this.api.search(keyword, _limit, _page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io());

    }
}
