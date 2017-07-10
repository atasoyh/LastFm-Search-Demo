package com.atasoyh.lastfmartistfinder.view.artistdetail;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.R;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;
import com.atasoyh.lastfmartistfinder.view.search.SearchFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoActivity extends BaseActivity {

    public static final String TAG_ARTIST = "artist";
    public static final String TAG_MBID = "mbid";
    private ArtistInfoFragment artistInfoFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_artistinfo);
        super.onCreate(savedInstanceState);
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String artist=getIntent().getStringExtra(TAG_ARTIST);
        String mbid=getIntent().getStringExtra(TAG_MBID);

        artistInfoFragment = (ArtistInfoFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (artistInfoFragment == null) {
            // Create the fragment
            artistInfoFragment = ArtistInfoFragment.newInstance(artist,mbid);
            activityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), artistInfoFragment, R.id.contentFrame);
        }

    }

    @Override
    protected void injectDependencies(DefaultApplication application) {
        application.getAppComponent().inject(this);

    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {

    }
}
