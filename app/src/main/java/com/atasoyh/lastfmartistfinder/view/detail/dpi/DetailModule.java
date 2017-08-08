package com.atasoyh.lastfmartistfinder.view.detail.dpi;

import com.atasoyh.lastfmartistfinder.interactor.album.AlbumInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.album.AlbumInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackInfoInteractorImpl;
import com.atasoyh.lastfmartistfinder.presenter.detail.DetailContract;
import com.atasoyh.lastfmartistfinder.presenter.detail.DetailPresenter;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 09/07/2017.
 */
@Module
public class DetailModule {

    private final String artistName;
    private final String albumName;
    private final String trackName;
    private final String mbid;
    private final SearchMoreFragment.Type type;

    private final DetailContract.View view;

    @Inject
    public DetailModule(DetailContract.View view, String artistName, String name, String mbid, SearchMoreFragment.Type type) {
        this.view = view;
        this.artistName = artistName;
        this.mbid = mbid;
        this.type = type;
        this.albumName = name;
        this.trackName = name;
    }

    @Provides
    DetailContract.View provideTasksContractView() {
        return view;
    }

    @DetailScope
    @Named("artistName")
    @Provides
    String getArtistName() {
        return artistName;
    }

    @DetailScope
    @Named("mbid")
    @Provides
    String getMbid() {
        return mbid;
    }

    @DetailScope
    @Named("albumName")
    @Provides
    public String getAlbumName() {
        return albumName;
    }

    @DetailScope
    @Named("trackName")
    @Provides
    public String getTrackName() {
        return trackName;
    }

    @DetailScope
    @Provides
    public SearchMoreFragment.Type getType() {
        return type;
    }

    @DetailScope
    @Provides
    ArtistInfoInteractor provideArtistInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("mbid") String mbid) {
        return new ArtistInfoInteractorImpl(api, artistName, mbid);
    }

    @DetailScope
    @Provides
    AlbumInfoInteractor provideAlbumInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("albumName") String albumName) {
        return new AlbumInfoInteractorImpl(api, albumName, artistName);
    }

    @DetailScope
    @Provides
    TrackInfoInteractor provideTrackInfoInteractor(LastFmApi api, @Named("artistName") String artistName, @Named("trackName") String trackName) {
        return new TrackInfoInteractorImpl(api, artistName, trackName);
    }

    @DetailScope
    @Provides
    DetailContract.Presenter provideArtistInfoPresenter(DetailContract.View view, ArtistInfoInteractor interactor, AlbumInfoInteractor albumInfoInteractor, TrackInfoInteractor trackInfoInteractor, SearchMoreFragment.Type type) {
        return new DetailPresenter(view, interactor, albumInfoInteractor, trackInfoInteractor, type);
    }


}
