package com.atasoyh.lastfmartistfinder.view.artistdetail.dpi;

import com.atasoyh.lastfmartistfinder.interactor.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.ArtistInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoPresenter;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchContract;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class ArtistInfoModule {


    String artistName;
    String mbid;

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

    @Named("artistName")
    @Provides
    String getArtistName() {
        return artistName;
    }

    @Named("mbid")
    @Provides
    String getMbid() {
        return mbid;
    }

    @Provides
    ArtistInfoInteractor provideArtistInfoInteractor(LastFmApi api) {
        return new ArtistInfoInteractorImpl(api);
    }

    @Provides
    ArtistInfoContract.Presenter provideSearchArtistPresenter(ArtistInfoContract.View view, ArtistInfoInteractor interactor, @Named("artistNane") String artistName, @Named("mbid") String mbid) {
        return new ArtistInfoPresenter(view, interactor, artistName, mbid);
    }


}
