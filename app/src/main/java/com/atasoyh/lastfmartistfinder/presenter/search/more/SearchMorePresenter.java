package com.atasoyh.lastfmartistfinder.presenter.search.more;

import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.Error;
import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.util.RetrofitException;
import com.atasoyh.lastfmartistfinder.util.TextUtils;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * SearchMorePresenter implementation
 * Created by atasoyh on 09/07/2017.
 */

public class SearchMorePresenter implements SearchMoreContract.Presenter {

    private final SearchMoreContract.View<BasePresenter> view;
    private final SearchInteractor artistInteractor;

    boolean onloading = false;

    int page = 1;
    int itemCountPerSearch = 30;
    String keyword;
    private SearchMoreFragment.Type type;
    private SearchInteractor albumInteractor;
    private SearchInteractor trackInteractor;


    @Inject
    public SearchMorePresenter(SearchMoreContract.View view, SearchMoreFragment.Type type, SearchInteractor artistInteractor) {
        this.view = view;
        this.artistInteractor = artistInteractor;
        this.type = type;

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
        artistInteractor.search(keyword, itemCountPerSearch, page).subscribe(getObserver());
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
                List<LastFMDisplayableInterface> lastFMDisplayableInterfaceList = new ArrayList<>();
                switch (type) {
                    case ARTIST:
                        lastFMDisplayableInterfaceList.addAll(response.getResults().getArtistMatches().getArtist());
                        break;
                    case ALBUM:
                        lastFMDisplayableInterfaceList.addAll(response.getResults().getAlbumMatches().getAlbum());
                        break;
                    case TRACK:
                        lastFMDisplayableInterfaceList.addAll(response.getResults().getTrackMatches().getTrack());
                        break;
                }
                view.addItems(lastFMDisplayableInterfaceList);

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
