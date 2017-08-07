package com.atasoyh.lastfmartistfinder.view.search.artist.dpi;

import com.atasoyh.lastfmartistfinder.view.search.artist.ArtistSearchFragment;

import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@ArtistSearchScope
@Subcomponent(modules = {ArtistSearchModule.class})
public interface ArtistSearchComponent {
    void inject(ArtistSearchFragment artistSearchFragment);
}
