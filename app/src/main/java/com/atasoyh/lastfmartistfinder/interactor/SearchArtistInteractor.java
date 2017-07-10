package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.Results;
import com.google.common.eventbus.Subscribe;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchArtistInteractor {

    LastFmApi api;

    @Inject
    public SearchArtistInteractor(LastFmApi api) {
        this.api = api;
    }

    public void execute(String keyword, Observer<com.atasoyh.lastfmartistfinder.model.Response> observer) {
        execute(keyword, -1, -1, observer);
    }

    public void execute(String keyword, int limit, int page, final Observer<com.atasoyh.lastfmartistfinder.model.Response> observer) {
        String _limit = String.valueOf(limit);
        String _page = String.valueOf(page);
        if (limit == -1 && page == -1) {
            _limit = null;
            _page = null;
        }
        this.api.search(keyword, _limit, _page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }


}
