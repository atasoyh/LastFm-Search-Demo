package com.atasoyh.lastfmartistfinder.view.artistdetail.dpi;

import com.atasoyh.lastfmartistfinder.interactor.SearchInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackSearchInteractorImpl;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoPresenter;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

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
    private final String albumName;
    private final String trackName;
    private final String mbid;
    private final SearchMoreFragment.Type type;

    private final ArtistInfoContract.View view;

    @Inject
    public ArtistInfoModule(ArtistInfoContract.View view, String artistName, String name, String mbid, SearchMoreFragment.Type type) {
        this.view = view;
        this.artistName = artistName;
        this.mbid = mbid;
        this.type = type;
        this.albumName = name;
        this.trackName = name;
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

    @ArtistInfoScope
    @Named("albumName")
    @Provides
    public String getAlbumName() {
        return albumName;
    }

    @ArtistInfoScope
    @Named("trackName")
    @Provides
    public String getTrackName() {
        return trackName;
    }

    @ArtistInfoScope
    @Provides
    public SearchMoreFragment.Type getType() {
        return type;
    }

    @ArtistInfoScope
    @Provides
    ArtistInfoInteractor provideArtistInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("mbid") String mbid) {
        return new ArtistInfoInteractorImpl(api, artistName, mbid);
    }

    @ArtistInfoScope
    @Provides
    AlbumInfoInteractor provideAlbumInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("albumName") String albumName) {
        return new AlbumInfoInteractorImpl(api, albumName, artistName);
    }

    @ArtistInfoScope
    @Provides
    TrackInfoInteractor provideTrackInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("trackName") String trackName) {
        return new TrackInfoInteractorImpl(api, artistName, trackName);
    }

    @ArtistInfoScope
    @Provides
    ArtistInfoContract.Presenter provideArtistInfoPresenter(ArtistInfoContract.View view, ArtistInfoInteractor interactor, AlbumInfoInteractor albumInfoInteractor, TrackInfoInteractor trackInfoInteractor, SearchMoreFragment.Type type) {
        return new ArtistInfoPresenter(view, interactor, albumInfoInteractor, trackInfoInteractor, type);
    }


}
