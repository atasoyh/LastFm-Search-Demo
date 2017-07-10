package com.atasoyh.lastfmartistfinder.presenter.artistinfo;

import com.atasoyh.lastfmartistfinder.interactor.ArtistInfoInteractor;
import com.atasoyh.lastfmartistfinder.model.response.GetArtistInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.SearchResponse;

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
    public void loadArtistInfo(String name, String mbid) {
        artistInfoInteractor.search(name, mbid).subscribe(getObserver());
    }

    private Observer<GetArtistInfoResponse> getObserver() {
        return new Observer<GetArtistInfoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetArtistInfoResponse response) {

                view.showLoading(false);

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
