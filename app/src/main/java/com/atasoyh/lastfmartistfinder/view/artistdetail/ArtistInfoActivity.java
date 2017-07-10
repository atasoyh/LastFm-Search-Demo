package com.atasoyh.lastfmartistfinder.view.artistdetail;

import com.atasoyh.lastfmartistfinder.DefaultApplication;
import com.atasoyh.lastfmartistfinder.view.BaseActivity;

/**
 * Created by atasoyh on 10/07/2017.
 */

public class ArtistInfoActivity extends BaseActivity {




    @Override
    protected void injectDependencies(DefaultApplication application) {
        application.getAppComponent().inject(this);
    }

    @Override
    protected void releaseSubComponents(DefaultApplication application) {

    }
}
