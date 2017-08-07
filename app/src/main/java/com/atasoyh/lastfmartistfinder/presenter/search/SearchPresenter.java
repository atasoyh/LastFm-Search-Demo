package com.atasoyh.lastfmartistfinder.presenter.search;

import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.model.AlbumMatches;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.Error;
import com.atasoyh.lastfmartistfinder.model.Results;
import com.atasoyh.lastfmartistfinder.model.SearchItems;
import com.atasoyh.lastfmartistfinder.model.TrackMatches;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.util.RetrofitException;
import com.atasoyh.lastfmartistfinder.util.TextUtils;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;

/**
 * Created by atasoyh on 07/08/2017.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View<BasePresenter> view;
    private final SearchInteractor<AlbumMatches> albumSearchInteractor;
    private final SearchInteractor<ArtistMatches> artistSearchInteractor;
    private final SearchInteractor<TrackMatches> trackSearchInteractor;

    boolean onloading = false;

    int page = 1;
    int itemCountPerSearch = 4;
    String keyword;


    @Inject
    public SearchPresenter(SearchContract.View view, SearchInteractor<AlbumMatches> albumSearchInteractor, SearchInteractor<ArtistMatches> artistSearchInteractor, SearchInteractor<TrackMatches> trackSearchInteractor) {
        this.view = view;
        this.albumSearchInteractor = albumSearchInteractor;
        this.artistSearchInteractor = artistSearchInteractor;
        this.trackSearchInteractor = trackSearchInteractor;

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
            return;
        }
        searchKeyword(keyword);
    }

    private void searchKeyword(String keyword) {
        onloading = true;
        view.showLoading(true);
        view.hideEmptyView();

        Observable<SearchResponse<ArtistMatches>> artistSearchObservable = artistSearchInteractor.search(keyword, itemCountPerSearch, page);
        Observable<SearchResponse<AlbumMatches>> albumSearchObservable = albumSearchInteractor.search(keyword, itemCountPerSearch, page);
        Observable<SearchResponse<TrackMatches>> trackSearchObservable = trackSearchInteractor.search(keyword, itemCountPerSearch, page);

        Observable.zip(artistSearchObservable, albumSearchObservable, trackSearchObservable, new Function3<SearchResponse<ArtistMatches>, SearchResponse<AlbumMatches>, SearchResponse<TrackMatches>, SearchItems>() {
            @Override
            public SearchItems apply(SearchResponse<ArtistMatches> artistSearchResponse, SearchResponse<AlbumMatches> albumSearchResponse, SearchResponse<TrackMatches> trackSearchResponse) throws Exception {
                SearchItems searchItems = new SearchItems();
                searchItems.setArtistList(((ArtistMatches) artistSearchResponse.getResults().getMatches()).getArtist());
                searchItems.setAlbumList(((AlbumMatches) albumSearchResponse.getResults().getMatches()).getAlbum());
                searchItems.setTrackList(((TrackMatches) trackSearchResponse.getResults().getMatches()).getTrack());
                return searchItems;
            }
        }).subscribe(getObserver());
    }

    private Observer<SearchItems> getObserver() {
        return new Observer<SearchItems>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchItems items) {
                onloading = false;
                view.showLoading(false);
                view.setItems(items);
                calculatePage(items);
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


    private void calculatePage(SearchItems items) {
        // page = Integer.parseInt(results.getOpensearchStartIndex());
//        {
//            int totalCount = Integer.parseInt(results.getOpensearchTotalResults());
//            if (page * itemCountPerSearch <= totalCount)
//                view.enableLoadMore();
//            else view.disableLoadMore();
//        }
    }



}
