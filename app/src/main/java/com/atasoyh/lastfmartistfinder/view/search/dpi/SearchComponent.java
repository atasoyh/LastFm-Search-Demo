package com.atasoyh.lastfmartistfinder.view.search.dpi;

import com.atasoyh.lastfmartistfinder.view.search.ArtistSearchFragment;

import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@SearchScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject(ArtistSearchFragment artistSearchFragment);
}
