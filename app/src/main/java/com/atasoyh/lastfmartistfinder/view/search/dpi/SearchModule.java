package com.atasoyh.lastfmartistfinder.view.search.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractorImpl;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class SearchModule {

    private final SearchContract.View view;

    @Inject
    public SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    SearchContract.View provideTasksContractView() {
        return view;
    }

    @Provides
    SearchArtistInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new SearchArtistInteractorImpl(api);
    }

    @Provides
    SearchContract.Presenter provideSearchArtistPresenter(SearchContract.View view, SearchArtistInteractor interactor) {
        return new SearchPresenter(view, interactor);
    }

}
