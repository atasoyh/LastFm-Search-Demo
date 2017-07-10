package com.atasoyh.lastfmartistfinder.view.artistdetail.dpi;

import com.atasoyh.lastfmartistfinder.view.artistdetail.ArtistInfoFragment;
import com.atasoyh.lastfmartistfinder.view.search.SearchFragment;

import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@ArtistInfoScope
@Subcomponent(modules = {ArtistInfoModule.class})
public interface ArtistInfoComponent {
    void inject(ArtistInfoFragment artistInfoFragment);
}
