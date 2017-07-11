package com.atasoyh.lastfmartistfinder.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by atasoyh on 09/07/2017.
 */

@Singleton
@Component(modules = {AppModule.class,ServiceModule.class, UtilityModule.class})
public interface AppComponent extends BaseAppComponent {
}
