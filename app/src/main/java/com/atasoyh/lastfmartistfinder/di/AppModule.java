package com.atasoyh.lastfmartistfinder.di;

import android.content.Context;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.util.ActivityUtils;
import com.atasoyh.lastfmartistfinder.util.TextUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final DefaultApplication mApplication;

    public AppModule(DefaultApplication app) {
        mApplication = app;
    }

    @Provides
    public Context getContext() {
        return mApplication;
    }



}
