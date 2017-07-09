package com.atasoyh.lastfmartistfinder.view.search;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.presenter.BasePresenter;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchPresenter;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@SearchScope
@Module
public class SearchModule {

    private final SearchContract.View view;


    public SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    SearchContract.View provideTasksContractView() {
        return view;
    }

    @Provides
    SearchArtistInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new SearchArtistInteractor(api);
    }

    @Provides
    SearchContract.SearchPresenter provideSearchArtistPresenter(SearchContract.View view, SearchArtistInteractor interactor) {
        return new SearchPresenter(view, interactor);
    }

}
