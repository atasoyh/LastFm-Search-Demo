package com.atasoyh.lastfmartistfinder.interactor;

import com.atasoyh.lastfmartistfinder.model.Results;
import com.google.common.eventbus.Subscribe;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface SearchArtistInteractor {

    Observable<com.atasoyh.lastfmartistfinder.model.Response> search(String keyword, int limit, int page);



}
