package com.atasoyh.lastfmartistfinder.di;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.presenter.artistinfo.ArtistInfoContract;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoComponent;
import com.atasoyh.lastfmartistfinder.view.artistdetail.dpi.ArtistInfoModule;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchComponent;
import com.atasoyh.lastfmartistfinder.view.search.dpi.SearchModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by atasoyh on 09/07/2017.
 */

@Singleton
@Component(modules = {AppModule.class,ServiceModule.class, UtilityModule.class})
public interface AppComponent {

    void inject(DefaultApplication defaultApplication);

    void inject(BaseActivity baseActivity);

    SearchComponent plus(SearchModule searchModule);

    ArtistInfoComponent plus(ArtistInfoModule artistInfoModule);



}
