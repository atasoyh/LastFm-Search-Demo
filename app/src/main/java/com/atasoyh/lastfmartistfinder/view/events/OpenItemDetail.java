package com.atasoyh.lastfmartistfinder.view.events;

import com.atasoyh.lastfmartistfinder.model.LastFMDisplayableInterface;
import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

/**
 * Created by atasoyh on 08/08/2017.
 */

public class OpenItemDetail {
    public LastFMDisplayableInterface item;
    public SearchMoreFragment.Type type;

    public OpenItemDetail(LastFMDisplayableInterface item, SearchMoreFragment.Type type) {
        this.item = item;
        this.type = type;
    }
}
