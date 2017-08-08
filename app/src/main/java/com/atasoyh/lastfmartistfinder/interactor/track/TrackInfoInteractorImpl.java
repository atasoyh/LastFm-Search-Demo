package com.atasoyh.lastfmartistfinder.interactor.track;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;
import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Track;
import com.atasoyh.lastfmartistfinder.model.response.GetAlbumInfoResponse;
import com.atasoyh.lastfmartistfinder.model.response.GetTrackInfoResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class TrackInfoInteractorImpl implements TrackInfoInteractor {

    LastFmApi api;

    @Inject
    public TrackInfoInteractorImpl(LastFmApi api) {
        this.api = api;
    }

    @Override
    public Observable<Track> getInfo(String track, String artistName) {
        return api.getTrackInfo(track,artistName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map((Function<GetTrackInfoResponse, Track>) getTrackInfoResponse -> getTrackInfoResponse.getTrack());
    }
}
