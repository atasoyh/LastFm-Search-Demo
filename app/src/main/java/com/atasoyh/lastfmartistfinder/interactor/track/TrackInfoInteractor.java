package com.atasoyh.lastfmartistfinder.interactor.track;

import com.atasoyh.lastfmartistfinder.model.Album;
import com.atasoyh.lastfmartistfinder.model.Track;

import io.reactivex.Observable;

/**
 * Created by atasoyh on 10/07/2017.
 */

public interface TrackInfoInteractor {
    Observable<Track> getInfo(String track, String artistName);

}
