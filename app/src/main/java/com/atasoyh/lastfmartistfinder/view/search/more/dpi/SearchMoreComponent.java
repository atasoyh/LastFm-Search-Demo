package com.atasoyh.lastfmartistfinder.view.search.more.dpi;

import com.atasoyh.lastfmartistfinder.view.search.more.SearchMoreFragment;

import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@SearchMoreScope
@Subcomponent(modules = {SearchMoreModule.class})
public interface SearchMoreComponent {
    void inject(SearchMoreFragment searchMoreFragment);
}
