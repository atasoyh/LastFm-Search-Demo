package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;

import javax.inject.Inject;

/**
 * SearchPresenter implementation
 * Created by atasoyh on 09/07/2017.
 */

public class SearchPresenter implements SearchContract.SearchPresenter {

    SearchContract.View view;
    LastFmApi api;

    @Inject
    public SearchPresenter(SearchContract.View view, LastFmApi api) {
        this.view = view;
        this.api = api;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void search(String keyword) {

    }

    @Override
    public void loadMore() {

    }
}
