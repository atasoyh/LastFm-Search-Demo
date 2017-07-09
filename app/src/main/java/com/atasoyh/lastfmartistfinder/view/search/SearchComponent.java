package com.atasoyh.lastfmartistfinder.view.search;

import com.atasoyh.lastfmartistfinder.di.AppComponent;
import com.atasoyh.lastfmartistfinder.presenter.search.SearchScope;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by atasoyh on 09/07/2017.
 */
@SearchScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject(SearchFragment searchFragment);
}
