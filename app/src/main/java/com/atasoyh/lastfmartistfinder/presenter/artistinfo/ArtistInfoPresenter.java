package com.atasoyh.lastfmartistfinder.presenter.artistinfo;

import com.atasoyh.lastfmartistfinder.interactor.album.AlbumInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.artist.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.interactor.track.TrackInfoInteractor;
import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.model.Bio;
import com.atasoyh.lastfmartistfinder.model.Error;
import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.model.Similar;
import com.atasoyh.lastfmartistfinder.model.Tags;
import com.atasoyh.lastfmartistfinder.util.RetrofitException;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoPresenter implements ArtistInfoContract.Presenter {

   private final ArtistInfoContract.View view;
    private final ArtistInfoInteractor artistInfoInteractor;
    private final AlbumInfoInteractor albumInfoInteractor;
    private final TrackInfoInteractor trackInfoInteractor;
    private final SearchMoreFragment.Type type;

    @Inject
    public ArtistInfoPresenter(ArtistInfoContract.View view, ArtistInfoInteractor artistInfoInteractor, AlbumInfoInteractor albumInfoInteractor, TrackInfoInteractor trackInfoInteractor, SearchMoreFragment.Type type) {
        this.artistInfoInteractor = artistInfoInteractor;
        this.albumInfoInteractor = albumInfoInteractor;
        this.trackInfoInteractor = trackInfoInteractor;
        this.view = view;
        this.type = type;
    }

    @Inject
    public void setupListener() {
        view.setPresenter(this);
    }


    @Override
    public void loadArtistInfo() {
        view.showLoading(true);
        switch (type) {
            case TRACK:
                trackInfoInteractor.getInfo().subscribe(getObserver());
                break;
            case ALBUM:
                albumInfoInteractor.getInfo().subscribe(getObserver());
                break;
            case ARTIST:
                artistInfoInteractor.getInfo().subscribe(getObserver());
                break;
        }

    }

    private Observer<LastFMDisplayableInterface> getObserver() {
        return new Observer<LastFMDisplayableInterface>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LastFMDisplayableInterface item) {
                view.showLoading(false);

                view.showImage(item.getMegaImageUrl());
                view.showName(item.getName());

                switch (type) {
                    case ARTIST:
                        Artist artist=(Artist)item;
                        Bio bio = artist.getBio();
                        if (bio != null)
                            view.showBio(bio);

                        Tags tags = artist.getTags();
                        if (tags != null)
                            view.showTags(tags);

                        Similar similar = artist.getSimilar();
                        if (similar != null)
                            view.showSimilars(artist.getSimilar());
                        break;
                    case TRACK:
                        break;
                    case ALBUM:
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                RetrofitException error = (RetrofitException) e;
                try {
                    Error response = error.getErrorBodyAs(Error.class);
                    view.showError(response.getMessage());
                } catch (Throwable e1) {
                    e1.printStackTrace();
                }
                view.showLoading(false);

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
