package com.atasoyh.lastfmartistfinder.interactor;

import java.util.Observable;

/**
 * Created by atasoyh on 09/07/2017.
 */

public interface LastFmApi {
    Observable search(String keyword);
}
