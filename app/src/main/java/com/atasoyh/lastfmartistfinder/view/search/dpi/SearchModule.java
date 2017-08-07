package com.atasoyh.lastfmartistfinder.view.search.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractor;
import com.atasoyh.lastfmartistfinder.interactor.SearchArtistInteractorImpl;
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class SearchModule {

    private final ArtistSearchContract.View view;

    @Inject
    public SearchModule(ArtistSearchContract.View view) {
        this.view = view;
    }

    @Provides
    ArtistSearchContract.View provideTasksContractView() {
        return view;
    }

    @Provides
    SearchArtistInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new SearchArtistInteractorImpl(api);
    }

    @Provides
    ArtistSearchContract.Presenter provideSearchArtistPresenter(ArtistSearchContract.View view, SearchArtistInteractor interactor) {
        return new ArtistSearchPresenter(view, interactor);
    }

}
