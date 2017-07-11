package com.atasoyh.lastfmartistfinder.di;

import android.content.Context;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

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

    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }


}
