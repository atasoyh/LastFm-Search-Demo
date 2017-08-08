package com.atasoyh.lastfmartistfinder.view.search.artist.dpi;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.model.AlbumMatches;
import com.atasoyh.lastfmartistfinder.model.ArtistMatches;
import com.atasoyh.lastfmartistfinder.model.TrackMatches;
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchContract;
import com.atasoyh.lastfmartistfinder.presenter.search.ArtistSearchPresenter;
import com.atasoyh.lastfmartistfinder.view.search.artist.ArtistSearchFragment;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class ArtistSearchModule {

    private final ArtistSearchContract.View view;
    private final ArtistSearchFragment.Type type;

    @Inject
    public ArtistSearchModule(ArtistSearchContract.View view, ArtistSearchFragment.Type type) {
        this.view = view;
        this.type = type;
    }

    @Provides
    ArtistSearchContract.View provideTasksContractView() {
        return view;
    }

    @Provides
    ArtistSearchFragment.Type provideType() {
        return type;
    }

    @Provides
    SearchInteractor provideSearchArtistInteractor(LastFmApi api) {
        return new ArtistSearchInteractorImpl(api);
    }


    @Provides
    ArtistSearchContract.Presenter provideSearchArtistPresenter(ArtistSearchContract.View view, SearchInteractor artistSearchInteractor) {
        return new ArtistSearchPresenter(view, artistSearchInteractor);
    }

}
