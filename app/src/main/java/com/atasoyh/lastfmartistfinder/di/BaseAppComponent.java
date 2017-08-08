package com.atasoyh.lastfmartistfinder.di;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoComponent;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoModule;
import com.atasoyh.lastfmartistfinder.view.search.more.dpi.SearchMoreComponent;
import com.atasoyh.lastfmartistfinder.view.search.more.dpi.SearchMoreModule;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchModule;

/**
 * Created by atasoyh on 11/07/2017.
 */

public interface BaseAppComponent {
    void inject(DefaultApplication defaultApplication);

    void inject(BaseActivity baseActivity);

    SearchMoreComponent plus(SearchMoreModule searchMoreModule);

    ArtistInfoComponent plus(ArtistInfoModule artistInfoModule);

    SearchComponent plus(SearchModule searchModule);
}
