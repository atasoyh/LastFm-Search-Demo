package com.atasoyh.lastfmartistfinder.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.util.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by atasoyh on 09/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(DefaultApplication.get(this));
    }

    protected abstract void injectDependencies(DefaultApplication application);

    @Override
    public void finish() {
        super.finish();
        releaseSubComponents(DefaultApplication.get(this));
    }

    protected abstract void releaseSubComponents(DefaultApplication application);
}
