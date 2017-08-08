package com.atasoyh.lastfmartistfinder.view.events;

import com.atasoyh.lastfmartistfinder.model.Artist;
import com.atasoyh.lastfmartistfinder.view.search.artist.ArtistSearchFragment;

/**
 * Created by atasoyh on 08/08/2017.
 */

public class ShowMoreResult {
    public ArtistSearchFragment.Type type;
    public String keyword;

    public ShowMoreResult(ArtistSearchFragment.Type type,String keyword) {
        this.type = type;
        this.keyword=keyword;
    }
}
