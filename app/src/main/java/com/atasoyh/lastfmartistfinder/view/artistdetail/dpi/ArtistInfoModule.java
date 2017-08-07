package com.atasoyh.lastfmartistfinder.view.artistdetail.dpi;

import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoPresenter;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class ArtistInfoModule {

    private final String artistName;
    private final String mbid;

    private final ArtistInfoContract.View view;

    @Inject
    public ArtistInfoModule(ArtistInfoContract.View view, String artistName, String mbid) {
        this.view = view;
        this.artistName = artistName;
        this.mbid = mbid;
    }

    @Provides
    ArtistInfoContract.View provideTasksContractView() {
        return view;
    }

    @ArtistInfoScope
    @Named("artistName")
    @Provides
    String getArtistName() {
        return artistName;
    }

    @ArtistInfoScope
    @Named("mbid")
    @Provides
    String getMbid() {
        return mbid;
    }

    @Provides
    ArtistInfoInteractor provideArtistInfoInteractor(LastFmApi api) {
        return new ArtistInfoInteractorImpl(api);
    }

    @ArtistInfoScope
    @Provides
    ArtistInfoContract.Presenter provideArtistInfoPresenter(ArtistInfoContract.View view, ArtistInfoInteractor interactor, @Named("artistName") String artistName, @Named("mbid") String mbid) {
        return new ArtistInfoPresenter(view, interactor, artistName, mbid);
    }


}
