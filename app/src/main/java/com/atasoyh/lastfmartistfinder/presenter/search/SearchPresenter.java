package com.atasoyh.lastfmartistfinder.presenter.search;

import android.text.TextUtils;

import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.model.Response;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * SearchPresenter implementation
 * Created by atasoyh on 09/07/2017.
 */

public class SearchPresenter implements SearchContract.SearchPresenter {

    SearchContract.View<BasePresenter> view;
    SearchArtistInteractor interactor;

    boolean onloading=false;

    int page = 1;
    int itemCountPerSearch = 30;
    String keyword;

    @Inject
    public SearchPresenter(SearchContract.View view, SearchArtistInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void search(String keyword) {
        view.refreshItems();
        this.keyword=keyword;
        if (TextUtils.isEmpty(keyword)) {
            view.disableLoadMore();
            return;
        }
        searchKeyword(keyword);
    }

    private void searchKeyword(String keyword) {
        onloading=true;
        view.showLoading(true);
        interactor.execute(keyword,itemCountPerSearch,page, getObserver());
    }

    private Observer<Response> getObserver() {
        return new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response response) {
                onloading=false;
                view.showLoading(false);
                view.addItems(response.getResults().getArtistmatches().getArtist());
                calculatePage(response.getResults());
            }

            @Override
            public void onError(Throwable e) {
                view.refreshItems();
                onloading=false;
            }

            @Override
            public void onComplete() {

            }
        };
    }


    private void calculatePage(Results results) {
       // page = Integer.parseInt(results.getOpensearchStartIndex());
        int totalCount = Integer.parseInt(results.getOpensearchTotalResults());
        if (page * itemCountPerSearch <= totalCount)
            view.enableLoadMore();
        else view.disableLoadMore();

    }


    @Override
    public void loadMore() {
        if(onloading) return;
        page++;
        searchKeyword(keyword);

    }
}
