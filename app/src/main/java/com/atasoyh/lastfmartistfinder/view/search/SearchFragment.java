package com.atasoyh.lastfmartistfinder.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.view.BaseFragment;

import java.util.List;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View<SearchContract.SearchPresenter> {

    SearchContract.SearchPresenter presenter;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultApplication.get(getContext()).getAppComponent().plus(new SearchModule(this)).inject(this);
    }


    @Override
    public void setPresenter(SearchContract.SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading(boolean visible) {
        if (visible) {
        } else {
        }

    }

    @Override
    public void addItems(List<Artist> items) {

    }

    @Override
    public void refreshItems() {

    }

    @Override
    public void enableLoadMore() {

    }

    @Override
    public void disableLoadMore() {

    }
}
