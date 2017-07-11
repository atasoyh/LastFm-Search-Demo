package com.atasoyh.lastfmartistfinder;

import android.app.Application;
import android.content.Context;

import com.atasoyh.lastfmartistfinder.di.AppModule;
import com.atasoyh.lastfmartistfinder.di.BaseAppComponent;
import com.atasoyh.lastfmartistfinder.di.DaggerAppComponent;
import com.atasoyh.lastfmartistfinder.di.ServiceModule;
import com.atasoyh.lastfmartistfinder.di.UtilityModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by atasoyh on 09/07/2017.
 */

public class DefaultApplication extends Application {

    public BaseAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //facebook's imageloader library
        Fresco.initialize(this);
        //create appComponent
        appComponent = createAppComponent();
        appComponent.inject(this);
    }

    public BaseAppComponent createAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).serviceModule(new ServiceModule()).utilityModule(new UtilityModule()).build();
    }

    public BaseAppComponent getAppComponent() {
        return appComponent;
    }


    public static DefaultApplication get(Context context) {
        return (DefaultApplication) context.getApplicationContext();
    }


}
