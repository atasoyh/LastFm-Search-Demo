package com.atasoyh.lastfmartistfinder.view.search.more.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchMoreContract;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchMorePresenter;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class SearchMoreModule {

    private final SearchMoreContract.View view;
    private final SearchMoreFragment.Type type;

    @Inject
    public SearchMoreModule(SearchMoreContract.View view, SearchMoreFragment.Type type) {
        this.view = view;
        this.type = type;
    }

    @Provides
    SearchMoreContract.View provideTasksContractView() {
        return view;
    }

    @Provides
    SearchMoreFragment.Type provideType() {
        return type;
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
    SearchMoreContract.Presenter provideSearchArtistPresenter(SearchMoreContract.View view, SearchMoreFragment.Type type, @Named("ArtistInteractor") SearchInteractor artistSearchInteractor, @Named("AlbumInteractor") SearchInteractor albumSearchInteractor, @Named("TrackInteractor") SearchInteractor trackSearchInteractor) {

        switch (type) {
            case ALBUM:
                return new SearchMorePresenter(view, type, albumSearchInteractor);
            case TRACK:
                return new SearchMorePresenter(view, type, trackSearchInteractor);
            case ARTIST:
                return new SearchMorePresenter(view, type, artistSearchInteractor);
            default:
                return null;

        }
    }

}
