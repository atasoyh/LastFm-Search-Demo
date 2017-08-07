package com.atasoyh.lastfmartistfinder.view.search.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.model.AlbumMatches;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.TrackMatches;
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchPresenter;

import javax.inject.Inject;
import javax.inject.Named;

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

    @Named("ArtistInteractor")
    @Provides
    SearchInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new ArtistSearchInteractorImpl(api);
    }

    @Named("AlbumInteractor")
    @Provides
    SearchInteractor provideAlbumSearchInteractor(LastFmApi api) {
        return new AlbumSearchInteractorImpl(api);
    }

    @Named("TrackInteractor")
    @Provides
    SearchInteractor provideTrackSearchInteractor(LastFmApi api) {
        return new TrackSearchInteractorImpl(api);
    }


    @Provides
    SearchContract.Presenter provideSearchPresenter(SearchContract.View view, @Named("ArtistInteractor") SearchInteractor artistSearchInteractor, @Named("AlbumInteractor") SearchInteractor albumSearchInteractor, @Named("TrackInteractor") SearchInteractor trackSearchInteractor) {
        return new SearchPresenter(view, albumSearchInteractor, artistSearchInteractor, trackSearchInteractor);
    }

}
