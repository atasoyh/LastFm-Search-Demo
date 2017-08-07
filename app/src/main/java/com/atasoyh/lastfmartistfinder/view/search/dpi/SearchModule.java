package com.atasoyh.lastfmartistfinder.view.search.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistSearchInteractorImpl;
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
    SearchInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new ArtistSearchInteractorImpl(api);
    }

    @Provides
    ArtistSearchContract.Presenter provideSearchArtistPresenter(ArtistSearchContract.View view, SearchInteractor interactor) {
        return new ArtistSearchPresenter(view, interactor);
    }

}
