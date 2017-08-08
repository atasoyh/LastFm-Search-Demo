package com.atasoyh.lastfmartistfinder.view.events;

import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

/**
 * Created by atasoyh on 08/08/2017.
 */

public class ShowMoreResult {
    public SearchMoreFragment.Type type;
    public String keyword;

    public ShowMoreResult(SearchMoreFragment.Type type, String keyword) {
        this.type = type;
        this.keyword=keyword;
    }
}
