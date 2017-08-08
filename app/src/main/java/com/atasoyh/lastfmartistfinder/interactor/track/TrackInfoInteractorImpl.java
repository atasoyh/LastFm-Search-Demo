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

    private final String artistName;
    private final String trackName;
    LastFmApi api;

    @Inject
    public TrackInfoInteractorImpl(LastFmApi api,String artistName,String trackName) {
        this.api = api;
        this.artistName = artistName;
        this.trackName = trackName;
    }

    @Override
    public Observable<Track> getInfo() {
        return api.getTrackInfo(trackName,artistName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map((Function<GetTrackInfoResponse, Track>) getTrackInfoResponse -> getTrackInfoResponse.getTrack());
    }
}
