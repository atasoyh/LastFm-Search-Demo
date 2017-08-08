package com.atasoyh.lastfmartistfinder.view.detail.dpi;

import com.atasoyh.lastfmartistfinder.view.detail.DetailFragment;

import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@DetailScope
@Subcomponent(modules = {DetailModule.class})
public interface DetailComponent {
    void inject(DetailFragment detailFragment);
}
