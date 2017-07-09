package com.atasoyh.lastfmartistfinder.interactor;

import android.text.TextUtils;

import com.atasoyh.lastfmartistfinder.model.Results;

import java.lang.annotation.Inherited;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Single;
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

    public void execute(String keyword,Observer<Results> observer) {
        execute(keyword, 0, 0,observer);
    }

    public void execute(String keyword, int limit, int page, final Observer<Results> observer) {
        String _limit=null;
        if(limit!=0) _limit=String.valueOf(limit);

        String _page=null;
        if(page!=0) _page=String.valueOf(page);

        this.api.search(keyword, _limit, _page).enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                observer.onNext(response.body());
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

}
