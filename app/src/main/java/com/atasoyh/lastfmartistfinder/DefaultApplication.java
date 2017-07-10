package com.atasoyh.lastfmartistfinder;

import android.app.Application;
import android.content.Context;

import com.atasoyh.lastfmartistfinder.di.AppModule;
import com.atasoyh.lastfmartistfinder.di.DaggerAppComponent;
import com.atasoyh.lastfmartistfinder.di.ServiceModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class DefaultApplication extends Application {

    private DaggerAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //facebook's imageloader library
        Fresco.initialize(this);
        //create appComponent
        createAppComponent();
    }

    private void createAppComponent() {
        appComponent = (DaggerAppComponent) DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public DaggerAppComponent getAppComponent() {
        return appComponent;
    }


    public static DefaultApplication get(Context context) {
        return (DefaultApplication) context.getApplicationContext();
    }


}
