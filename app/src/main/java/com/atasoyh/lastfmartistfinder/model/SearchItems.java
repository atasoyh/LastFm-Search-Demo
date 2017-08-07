package com.atasoyh.lastfmartistfinder.model;

import com.atasoyh.lastfmartistfinder.interactor.LastFmApi;

import java.util.List;

/**
 * Created by atasoyh on 07/08/2017.
 */

public class SearchItems {
    private List<Artist> artistList;
    private List<Album> albumList;
    private List<Track> trackList;

    public List<Artist> getArtistList() {
        return artistList;
    }

    public SearchItems setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
        return this;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public SearchItems setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        return this;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public SearchItems setTrackList(List<Track> trackList) {
        this.trackList = trackList;
        return this;
    }
}
