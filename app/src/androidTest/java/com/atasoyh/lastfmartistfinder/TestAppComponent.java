package com.atasoyh.lastfmartistfinder;


import com.atasoyh.lastfmartistfinder.di.AppModule;
import com.atasoyh.lastfmartistfinder.di.BaseAppComponent;
import com.atasoyh.lastfmartistfinder.di.ServiceModule;
import com.atasoyh.lastfmartistfinder.di.UtilityModule;
import com.atasoyh.lastfmartistfinder.view.search.SearchActivityTest;

import dagger.Component;

/**
 * Created by atasoyh on 11/07/2017.
 */
@Component(modules = {AppModule.class,TestServiceModule.class, UtilityModule.class})
public interface TestAppComponent extends BaseAppComponent {

    void inject(SearchActivityTest searchActivityTest);
}
