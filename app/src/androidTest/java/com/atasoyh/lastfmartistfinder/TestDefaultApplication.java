package com.atasoyh.lastfmartistfinder;


import com.atasoyh.lastfmartistfinder.di.BaseAppComponent;
import com.atasoyh.lastfmartistfinder.di.UtilityModule;

/**
 * Created by atasoyh on 11/07/2017.
 */

public class TestDefaultApplication extends DefaultApplication {

    @Override
    public BaseAppComponent createAppComponent() {
        return DaggerTestAppComponent.builder().testServiceModule(new TestServiceModule()).utilityModule(new UtilityModule()).build();
    }
}
