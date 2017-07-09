package com.atasoyh.lastfmartistfinder.presenter.search;

import android.text.TextUtils;

import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;

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

    int page = 0;
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
        if (TextUtils.isEmpty(keyword)) {
            view.disableLoadMore();
            view.addItems(null);
            return;
        }
        view.showLoading(true);
        interactor.execute(keyword,itemCountPerSearch,page, getObserver());
    }

    private Observer<Results> getObserver() {
        return new Observer<Results>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Results results) {
                view.showLoading(false);
                view.addItems(results.getArtistmatches().getArtist());
                calculatePage(results);
            }

            @Override
            public void onError(Throwable e) {
                view.refreshItems();

            }

            @Override
            public void onComplete() {

            }
        };
    }


    private void calculatePage(Results results) {
        page = Integer.parseInt(results.getOpensearchStartIndex());
        int totalCount = Integer.parseInt(results.getOpensearchTotalResults());
        if (page * itemCountPerSearch <= totalCount)
            view.enableLoadMore();
        else view.disableLoadMore();

    }


    @Override
    public void loadMore() {
        page++;
        search(keyword);

    }
}
