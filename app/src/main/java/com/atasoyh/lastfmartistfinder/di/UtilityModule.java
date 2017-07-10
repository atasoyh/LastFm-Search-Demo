package com.atasoyh.lastfmartistfinder.di;

import com.atasoyh.lastfmartistfinder.util.ActivityUtils;
import com.atasoyh.lastfmartistfinder.util.TextUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atasoyh on 10/07/2017.
 */
@Module
public class UtilityModule {

    @Provides
    public TextUtils provideTextUtils(){
        return new TextUtils();
    }

    @Provides
    public ActivityUtils provideActivityUtils(){
        return new ActivityUtils();
    }
}
