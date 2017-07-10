package com.atasoyh.lastfmartistfinder.presenter.artistinfo;

import com.atasoyh.lastfmartistfinder.interactor.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.model.Artist;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoPresenter implements ArtistInfoContract.Presenter {

    private final String artistName;
    private final String mbid;
    private final ArtistInfoContract.View view;
    private final ArtistInfoInteractor artistInfoInteractor;

    @Inject
    public ArtistInfoPresenter(ArtistInfoContract.View view, ArtistInfoInteractor artistInfoInteractor, String artistName, String mbid) {
        this.artistInfoInteractor = artistInfoInteractor;
        this.view = view;
        this.artistName = artistName;
        this.mbid = mbid;
    }

    @Inject
    public void setupListener() {
        view.setPresenter(this);
    }


    @Override
    public void loadArtistInfo() {
        view.showLoading(true);
        artistInfoInteractor.getInfo(artistName, mbid).subscribe(getObserver());
    }

    private Observer<Artist> getObserver() {
        return new Observer<Artist>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Artist response) {
                view.showLoading(false);
                view.showBio(response.getBio());
                view.showTags(response.getTags());
                view.showImage(response.getLargeImageUrl());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
