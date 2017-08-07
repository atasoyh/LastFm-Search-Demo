package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.Error;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.util.RetrofitException;
import com.atasoyh.lastfmartistfinder.util.TextUtils;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ArtistSearchPresenter implementation
 * Created by atasoyh on 09/07/2017.
 */

public class ArtistSearchPresenter implements ArtistSearchContract.Presenter {

    private final ArtistSearchContract.View<BasePresenter> view;
    private final SearchArtistInteractor interactor;

    boolean onloading = false;

    int page = 1;
    int itemCountPerSearch = 30;
    String keyword;


    @Inject
    public ArtistSearchPresenter(ArtistSearchContract.View view, SearchArtistInteractor interactor) {
        this.view = view;
        this.interactor = interactor;

    }

    @Inject
    @Override
    public void setupListener() {
        view.setPresenter(this);
    }

    @Override
    public void search(String keyword) {
        view.refreshItems();
        view.showEmptyView();
        this.keyword = keyword;
        if (TextUtils.isEmpty(keyword)) {
            view.disableLoadMore();
            return;
        }
        searchKeyword(keyword);
    }

    private void searchKeyword(String keyword) {
        onloading = true;
        view.showLoading(true);
        view.hideEmptyView();
        interactor.search(keyword, itemCountPerSearch, page).subscribe(getObserver());
    }

    private Observer<SearchResponse> getObserver() {
        return new Observer<SearchResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchResponse response) {
                onloading = false;
                view.showLoading(false);
                view.addItems(((ArtistMatches)response.getResults().getMatches()).getArtist());
                calculatePage(response.getResults());
            }

            @Override
            public void onError(Throwable e) {

                RetrofitException error = (RetrofitException) e;

                try {
                    Error response = error.getErrorBodyAs(Error.class);
                    view.showError(response.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                view.refreshItems();
                view.showEmptyView();
                view.showLoading(false);
                onloading = false;

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
        if (onloading) return;
        page++;
        searchKeyword(keyword);

    }


}
